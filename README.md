# Tourism-Project
Project for STIA1123A (Programming 2) course

## Backend Branch
The back-end branch contains package that can be used to
perform client-server interactions.  
Maintained by @dfx81.  
  
Contents of Backend.jar:
- backend.Client
- backend.Server
- backend.Worker
- backend.RequestHandler

To use, simply add the Backend.jar file as an external library
and you can start using the classes. Backend.jar available in
the Packages directory at the root of the repo.

## Demo
Example program for both the Client and the Server is also
available. Run the ExampleServer program first then start running
ExampleClient. All the program does is playing a simple game of
rock paper scissors until "/exit" was sent. Shut down the server
with "/exit".

## Progress
Most of the classes are working as intended and simple enough to be
used in any form of interface (CLI, Swing, JavaFX, Android, etc).