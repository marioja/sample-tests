The following errors occur when deploying this simpleEar module to WAS 8.5.5.14:

    [21/08/22 17:43:47:538 EDT] 000000a1 InstallSchedu I   ADMA5016I: Installation of simpleEar started.
    [21/08/22 17:43:47:948 EDT] 000000a1 wtp           E org.eclipse.jst.j2ee.commonarchivecore.internal.impl.EARFileImpl rollUpRoles An error occurred while processing the roles in module 
        Web Module        [ org.eclipse.jst.j2ee.application.internal.impl.WebModuleImpl@3336a575 ]
        URI               [ dev.sample.skinny.wars-simpleWar-1.0.0-SNAPSHOT.war ]
        Alt DD            [ null ]
        Context Root      [ /simple ]
     for application C:\radws\workspace\simple-parent\simple-parent-SKINNY_WAR\simpleEar\src\main\application, error message=A file does not exist for module element having uri: dev.sample.skinny.wars-simpleWar-1.0.0-SNAPSHOT.war 
    [21/08/22 17:43:47:988 EDT] 000000a1 FfdcProvider  W com.ibm.ws.ffdc.impl.FfdcProvider logIncident FFDC1003I: FFDC Incident emitted on c:\radws\profile\pikssyncWASProfile\logs\ffdc\server1_3ed402da_22.08.21_17.43.47.9771876556434545111357.txt com.ibm.ws.management.application.task.ValidateAppTask.performTask 469
    [21/08/22 17:43:48:001 EDT] 000000a1 FfdcProvider  W com.ibm.ws.ffdc.impl.FfdcProvider logIncident FFDC1003I: FFDC Incident emitted on c:\radws\profile\pikssyncWASProfile\logs\ffdc\server1_3ed402da_22.08.21_17.43.47.9892028978412074815746.txt com.ibm.ws.management.application.SchedulerImpl.run 328
    [21/08/22 17:43:48:007 EDT] 000000a1 InstallSchedu I   ADMA5014E: The installation of application simpleEar failed.
    [21/08/22 17:43:48:002 EDT] 000000a1 SystemErr     R org.eclipse.jst.j2ee.commonarchivecore.internal.exception.DeploymentDescriptorLoadException: dd_in_ear_load_EXC_
    Stack trace of nested exception:
    org.eclipse.jst.j2ee.commonarchivecore.internal.exception.NoModuleFileException: A file does not exist for module element having uri: dev.sample.skinny.wars-simpleWar-1.0.0-SNAPSHOT.war
      at org.eclipse.jst.j2ee.commonarchivecore.internal.impl.ModuleRefImpl.checkType(ModuleRefImpl.java:591)
      at org.eclipse.jst.j2ee.commonarchivecore.internal.impl.ModuleRefImpl.initModuleFileFromEAR(ModuleRefImpl.java:167)
      at org.eclipse.jst.j2ee.commonarchivecore.internal.impl.ModuleRefImpl.getModuleFile(ModuleRefImpl.java:120)
      at org.eclipse.jst.j2ee.commonarchivecore.internal.impl.EARFileImpl.getModuleFile(EARFileImpl.java:166)
      at org.eclipse.jst.j2ee.commonarchivecore.internal.impl.EARFileImpl.getDeploymentDescriptor(EARFileImpl.java:842)
      at org.eclipse.jst.j2ee.commonarchivecore.internal.impl.EARFileImpl.getDeploymentDescriptor(EARFileImpl.java:814)
      at org.eclipse.jst.j2ee.commonarchivecore.internal.impl.EARFileImpl.getDeploymentDescriptor(EARFileImpl.java:862)
      at org.eclipse.jst.j2ee.commonarchivecore.internal.impl.EARFileImpl.rollUpRoles(EARFileImpl.java:1726)
      at org.eclipse.jst.j2ee.commonarchivecore.internal.impl.EARFileImpl.rollUpRoles(EARFileImpl.java:1707)
      at com.ibm.websphere.management.application.client.ArchiveDeploymentInfo.<init>(ArchiveDeploymentInfo.java:271)
      at com.ibm.ws.management.application.client.AppInstallHelper.getAppDeploymentInfo(AppInstallHelper.java:596)
      at com.ibm.ws.management.application.task.ValidateAppTask.performTask(ValidateAppTask.java:313)
      at com.ibm.ws.management.application.SchedulerImpl.run(SchedulerImpl.java:315)
      at java.lang.Thread.run(Thread.java:811)
    
    [21/08/22 17:43:48:002 EDT] 000000a1 SystemErr     R Stack trace of nested exception:
    [21/08/22 17:43:48:003 EDT] 000000a1 SystemErr     R org.eclipse.jst.j2ee.commonarchivecore.internal.exception.NoModuleFileException: A file does not exist for module element having uri: dev.sample.skinny.wars-simpleWar-1.0.0-SNAPSHOT.war
    [21/08/22 17:43:48:003 EDT] 000000a1 SystemErr     R  at org.eclipse.jst.j2ee.commonarchivecore.internal.impl.ModuleRefImpl.checkType(ModuleRefImpl.java:591)
    [21/08/22 17:43:48:003 EDT] 000000a1 SystemErr     R  at org.eclipse.jst.j2ee.commonarchivecore.internal.impl.ModuleRefImpl.initModuleFileFromEAR(ModuleRefImpl.java:167)
    [21/08/22 17:43:48:003 EDT] 000000a1 SystemErr     R  at org.eclipse.jst.j2ee.commonarchivecore.internal.impl.ModuleRefImpl.getModuleFile(ModuleRefImpl.java:120)
    [21/08/22 17:43:48:003 EDT] 000000a1 SystemErr     R  at org.eclipse.jst.j2ee.commonarchivecore.internal.impl.EARFileImpl.getModuleFile(EARFileImpl.java:166)
    [21/08/22 17:43:48:003 EDT] 000000a1 SystemErr     R  at org.eclipse.jst.j2ee.commonarchivecore.internal.impl.EARFileImpl.getDeploymentDescriptor(EARFileImpl.java:842)
    [21/08/22 17:43:48:003 EDT] 000000a1 SystemErr     R  at org.eclipse.jst.j2ee.commonarchivecore.internal.impl.EARFileImpl.getDeploymentDescriptor(EARFileImpl.java:814)
    [21/08/22 17:43:48:003 EDT] 000000a1 SystemErr     R  at org.eclipse.jst.j2ee.commonarchivecore.internal.impl.EARFileImpl.getDeploymentDescriptor(EARFileImpl.java:862)
    [21/08/22 17:43:48:003 EDT] 000000a1 SystemErr     R  at org.eclipse.jst.j2ee.commonarchivecore.internal.impl.EARFileImpl.rollUpRoles(EARFileImpl.java:1726)
    [21/08/22 17:43:48:003 EDT] 000000a1 SystemErr     R  at org.eclipse.jst.j2ee.commonarchivecore.internal.impl.EARFileImpl.rollUpRoles(EARFileImpl.java:1707)
    [21/08/22 17:43:48:003 EDT] 000000a1 SystemErr     R  at com.ibm.websphere.management.application.client.ArchiveDeploymentInfo.<init>(ArchiveDeploymentInfo.java:271)
    [21/08/22 17:43:48:003 EDT] 000000a1 SystemErr     R  at com.ibm.ws.management.application.client.AppInstallHelper.getAppDeploymentInfo(AppInstallHelper.java:596)
    [21/08/22 17:43:48:003 EDT] 000000a1 SystemErr     R  at com.ibm.ws.management.application.task.ValidateAppTask.performTask(ValidateAppTask.java:313)
    [21/08/22 17:43:48:003 EDT] 000000a1 SystemErr     R  at com.ibm.ws.management.application.SchedulerImpl.run(SchedulerImpl.java:315)
    [21/08/22 17:43:48:003 EDT] 000000a1 SystemErr     R  at java.lang.Thread.run(Thread.java:811)
This error occurs because the looseconfig.xmi generated by the IBM M2E plugins uses the wrong web URI (different from the maven defaults generated in the application.xml) as illustrated by the generated looseconfig.xmi below:

    <?xml version="1.0" encoding="UTF-8"?>
    <org.eclipse.jst.j2ee.commonarchivecore.looseconfig:LooseArchive xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:org.eclipse.jst.j2ee.commonarchivecore.looseconfig="commonarchive.looseconfig.xmi" uri="C:\radws\workspace\simple-parent\simple-parent-SKINNY_WAR\simpleEar\src\main\application" binariesPath="C:\radws\workspace\simple-parent\simple-parent-SKINNY_WAR\simpleEar\src\main\application" resourcesPath="C:\radws\workspace\simple-parent\simple-parent-SKINNY_WAR\simpleEar\src\main\application" archiveType="EAR">
      <looseChildren uri="simpleWar-1.0.0-SNAPSHOT.war" binariesPath="C:/radws/workspace/simple-parent/.metadata/.plugins/org.eclipse.wst.server.core/tmp0\simpleWar" resourcesPath="C:/radws/workspace/simple-parent/.metadata/.plugins/org.eclipse.wst.server.core/tmp0\simpleWar" archiveType="WAR"/>
      <looseChildren uri="lib/slf4j-api-1.7.28.jar" binariesPath="C:\Users\mxj037\.m2\repository\org\slf4j\slf4j-api\1.7.28\slf4j-api-1.7.28.jar" resourcesPath="C:\Users\mxj037\.m2\repository\org\slf4j\slf4j-api\1.7.28\slf4j-api-1.7.28.jar" archiveType="SIMPLEJAR"/>
    </org.eclipse.jst.j2ee.commonarchivecore.looseconfig:LooseArchive>
    