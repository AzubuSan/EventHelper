EventHelper
===========

A plugin made to make running events easier for server staff.

Basic Commands and Permissions
==============================

Commands:
* /eh set [Parameters] | Sets event parameters, see below for list of parameters
* /eh get [Parameters] | Gets the set parameters, see below for list of parameters
* /eh tp [Event name] | Teleports to one of the set spawnpoints
* /eh startevent [name] | Start 

Command Parameters:
* <b>ID</b> | Set the ID for the event (Used to tell the difference between multiple events)
* <b>NAME</b> | Set the name of the event, used in broadcast messages and such
* <b>REWARDS</b> | Set the rewards handed out to players who fulfil the event criteria once it is ended/ends automatically, a inventory is opened in which you drag the rewards to be handed out

TODO
====

* <b>TIME</b> | If type is Timed, use this to set how long the event will run for, in seconds (E.g: 3600 = One Hour).
* <b>TYPE</b> | Set if the event is either Timed or Manual. A timed event is ended automatically after a set time period (in seconds)
* <b>SPAWNPOINTS</b> | Set the spawnpoints for players teleporting to the event, these are randomized, max amount is set in the config
* <b>MOBSPAWNS</b> | Set the blocks on which mobs are spawned, right click adds - left click removes
* <b>EVENTAREA</b> | Uses the powertool to set the area in which the event is taking place
