# sample-parent
If I have a component called say dev.comps:comp1 that has org.apache.commons:commons-text:1.10.0 in the dependencyManagement, when I build comp1 then commons-text:1.10.0 will be used even though one of its transitive dependencies might be for commons-text:1.9.

If I have another component call dev.apps:app1 that depends on comp1, when I build app1 then org.apache.commons:commons-text:1.9 will be used as the dependencyManagement in comp1 is not used or respected.