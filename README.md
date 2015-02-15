# bos-missionbuilder
Web-based mission builder for Il-2: BoS

Currently work-in-progress. The map system is getting there and the actual .Mission file generator on the java side can create working missions from unit tests. However, there is no glue between the GUI and the mission file yet.

The web GUI generates an intermediate mission format that abstracts away a lot of the complexity of the BoS/RoF mission file format. These intermediate files are plain JSON documents which currently are stored server-side in an embedded NoSQL database (ElasticSearch).

This software can be run by individual users on their local machines, but the long-term objective is to host this on Amazon EC2 or similar and provide an account system so users can log in and manage their missions there. Of course users will have the capability to export the mission to BoS format at any time. Will probably be a zip archive with the .Mission file and at least one .eng language / strings file.

License: MIT license
Frameworks Web: jQuery, Handlebars, Twitter Bootstrap
Frameworks Server: Spring Boot, ElasticSearch, Java 8

# TODO
Fix map drag, select etc. behaviour.
Fix bug where one cannot seect again after a select due to state.STATE problem.

