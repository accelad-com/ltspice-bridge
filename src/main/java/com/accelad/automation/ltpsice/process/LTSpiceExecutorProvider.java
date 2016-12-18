package com.accelad.automation.ltpsice.process;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

public class LTSpiceExecutorProvider {
    private static final Logger LOGGER = Logger.getLogger(LTSpiceExecutorProvider.class.getName());
    private static final String LTSPICE_PATH = "ltspice_path";
    private static final String LTSPICE_EXECUTABLE_NAME = "XVIIx86.exe";
    private final Preferences preferences;
    private final FileFinder finder;

    public LTSpiceExecutorProvider() {
        this(new FileFinder(LTSPICE_EXECUTABLE_NAME),
                Preferences.userNodeForPackage(LTSpiceExecutorProvider.class));
    }

    public LTSpiceExecutorProvider(FileFinder finder, Preferences preferences) {
        this.finder = finder;
        this.preferences = preferences;
    }

    public LTSpiceExecutor getExecutor() throws LTSpiceException {
        Optional<File> optionalExecutableFromPrefs = getExistingFileFromPreferences();
        if (optionalExecutableFromPrefs.isPresent()) {
            return optionalExecutableFromPrefs
                    .map(LTSpiceExecutor::new)
                    .get();
        }

        Optional<File> optionalExecutable = findExecutableInTheFullSystem();
        optionalExecutable.ifPresent(this::savePathInPreferences);

        return optionalExecutable
                .map(LTSpiceExecutor::new)
                .orElseThrow(() -> new LTSpiceException(
                        "Impossible to locate LTSpice. Please check if it has been installed on this computer."));
    }

    private void savePathInPreferences(File file) {
        preferences.put(LTSPICE_PATH, file.getAbsolutePath());
    }

    private Optional<File> getExistingFileFromPreferences() {
        String pathFromPreferences = preferences.get(LTSPICE_PATH, "");
        if ("".equals(pathFromPreferences)) {
            return Optional.empty();
        }
        File file = new File(pathFromPreferences);
        if (!file.exists()) {
            return Optional.empty();
        }
        return Optional.of(file);
    }

    private Optional<File> findExecutableInTheFullSystem() {
        try {
            List<Path> matchingPaths = new ArrayList<>();

            for (File drive : File.listRoots()) {
                List<Path> paths = searchExecutableInTheGivenDrive(drive);
                matchingPaths.addAll(paths);
            }
            return matchingPaths.stream()
                    .map(Path::toFile)
                    .findAny();

        } catch (IOException e) {
            LOGGER.log(Level.WARNING,
                    "The PathLocator was unable to find the LTSpice executable file.", e);
            return Optional.empty();
        }
    }

    private List<Path> searchExecutableInTheGivenDrive(File file)
            throws IOException {
        Files.walkFileTree(Paths.get(file.getPath()), finder);
        return finder.getMatchingPaths();
    }

}
