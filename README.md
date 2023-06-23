# sample-tests
Here are 3 sample use cases demonstrating issues with the IBM plugins. Here is a summary of the issue in each branch:
1. LOOSECONFIG_CLASSPATH_ISSUE - IBM Eclipse plugins correctly generates looseconfig.xmi but it is not reflected in classpath at runtime
1. SKINNY-FAILS - IBM Eclipse plugins incorrectly generates looseconfig.xmi that has wrong web URI
1. SKINNY-WORKS - slf4j jar is in the web classpath and should not at runtime