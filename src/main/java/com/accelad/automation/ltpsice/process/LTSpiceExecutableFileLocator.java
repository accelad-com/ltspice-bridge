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

import org.apache.commons.lang3.SystemUtils;

public class LTSpiceExecutableFileLocator {
    private static final Logger LOGGER = Logger.getLogger(LTSpiceExecutableFileLocator.class.getName());
    private static final String LTSPICE_PATH = "ltspice_path";
    private static final String LTSPICE_EXECUTABLE_NAME = "XVIIx??.exe";
    private final Preferences preferences;
    private final FileFinder finder;

    public LTSpiceExecutableFileLocator() {
        this(new FileFinder(LTSPICE_EXECUTABLE_NAME),
                getPreferences());
    }

    private static Preferences getPreferences() {
        try {
            return Preferences.userNodeForPackage(LTSpiceExecutableFileLocator.class);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Impossible to have access to user preferences", e);
            throw new RuntimeException("Impossible to have access to user preferences");
        }
    }

    public LTSpiceExecutableFileLocator(FileFinder finder, Preferences preferences) {
        this.finder = finder;
        this.preferences = preferences;
    }

    public Optional<ExecutableFile> locateExecutableFile() {
        Optional<ExecutableFile> optionalExecutableFromPrefs = getExistingFileFromPreferences();
        if (optionalExecutableFromPrefs.isPresent()) {
            return optionalExecutableFromPrefs;
        }

        if (SystemUtils.IS_OS_WINDOWS) {
            Optional<ExecutableFile> optionalExecutable = findExecutableInTheFullSystem();
            optionalExecutable.ifPresent(this::savePathInPreferences);
            return optionalExecutable;
        }

        if (SystemUtils.IS_OS_LINUX) {
            Optional<ExecutableFile> optionalExecutable = findExecutableinWineFolder();
            optionalExecutable.ifPresent(this::savePathInPreferences);
            return optionalExecutable;
        }

        return Optional.empty();

    }

    private Optional<ExecutableFile> findExecutableinWineFolder() {
        try {
            File driveCFolder = Wine.getDriveCFolder();

            List<Path> paths = searchExecutableInTheGivenDrive(driveCFolder);

            return paths.stream()
                    .map(Path::toFile)
                    .map(ExecutableFile::new)
                    .findAny();
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    private void savePathInPreferences(ExecutableFile file) {
        preferences.put(LTSPICE_PATH, file.getAbsolutePath());
    }

    private Optional<ExecutableFile> getExistingFileFromPreferences() {
        String pathFromPreferences = preferences.get(LTSPICE_PATH, "");
        if ("".equals(pathFromPreferences)) {
            return Optional.empty();
        }
        File file = new File(pathFromPreferences);
        if (!file.exists()) {
            return Optional.empty();
        }
        return Optional.of(new ExecutableFile(file));
    }

    private Optional<ExecutableFile> findExecutableInTheFullSystem() {
        try {
            List<Path> matchingPaths = new ArrayList<>();

            for (File drive : File.listRoots()) {
                List<Path> paths = searchExecutableInTheGivenDrive(drive);
                matchingPaths.addAll(paths);
            }
            return matchingPaths.stream()
                    .map(Path::toFile)
                    .map(ExecutableFile::new)
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
