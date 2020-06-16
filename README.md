# Tourism-Project
Project for STIA1123A (Programming 2) course

## Backend Branch
The back-end branch contains packages that can be used to
perform client-server interactions.  
Maintained by @dfx81.  
  
Contents:
- backend.Client
- backend.Server
- backend.ServerInstances

To use, simply get the package directory (src/backend) and
add to the classpath in your ide or during javac compilation.
To use any of the included classes, simply import the required
classes and use them like normal.

## Progress
Most of the classes are working as intended and simple enough to be
used in any form of interface (CLI, Swing, JavaFX, Android, etc).
However, there are some places that can be optimized.  

Areas of improvements:
- Change the name of ServerInstance class to something else
(Worker would be more appropriate).
- Separate response handling into separate class. So, no longer
necessary to edit the ServerInstance class.
