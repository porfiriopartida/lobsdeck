Usage:

Main class: com.porfiriopartida.obsdeck.ObsDockLauncher
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

The app will mind the spaces, and will try to find in the first section up to the first ','
Then it will switch to the next element scene
The pattern will just use starts with/ends with/contains/equals
See the @TransitionFacade file for these patterns.
If you switch to a window that you don't have in the files it shouldn't change to any screen


I have to update this but instead of importing I'm adding https://github.com/Twasi/websocket-obs-java code to mine,
this was not meant to be src controlled so... I'll fix it asap to be a dependency );

Use this code as is, just honor the Twasi library license, I don't mind what you use this for.
