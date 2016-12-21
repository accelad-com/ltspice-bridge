# LTSpice-Bridge

This code is used internally to benchmark our internal kernel.

This code can control the latest LTSpice version (XVII), which should be installed on the system before.

The main entry point is the LTSpiceService interface which should be implemented by the LTSpiceServiceImpl class.
The service can check if the environment is OK with the method isAvailable();

On Linux operating system, LTSpice is expected to run with Wine. 
On Windows operating system a typical installation should be supported.
