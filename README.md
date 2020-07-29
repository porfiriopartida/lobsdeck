Usage:

Main class: com.lopapa.obsdeck.ObsDockLauncher
Parameters: --file PathToYourConfigFile

Example:
Parameters: --file E:\files\GameDevConfigFile.properties

The file content should be something like:
```
Key,Scene,Validation
GIMP,Gimp,ENDS_WITH
IntelliJ IDEA,IntelliJ IDEA,ENDS_WITH
 Unity ,UnityScene,CONTAINS
Notepad,NotepadScene,CONTAINS
Blender,BlenderScene,STARTS_WITH
```