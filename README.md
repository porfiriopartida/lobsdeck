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

I have to update this but instead of importing I'm adding https://github.com/Twasi/websocket-obs-java code to mine,
this was not meant to be src controlled so... I'll fix it asap to be a dependency );
