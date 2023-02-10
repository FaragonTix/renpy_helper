# renpy_helper
Small program to transform text to renpy visual novell format
Requaire config file config.txt and start.txt with your text.

This program will also delete spaces in the start and in the end of line. Empty lines will be also deleted.



Example:
=

You have some text in start.txt file such as:
--------

Sam: I love this morning... 

I think, this wasn't true.



You want achieve this text (end.txt):
--

hide samsprite

show samsprite at

s"I love this morning..."

o"I think, this wasn't true."



so your config.txt file:
--

@notag=o 

Sam:;samsprite;s

If you want to use no tag lines without any letter just delete @notag option from config file.



Additional options was added:
=

@show_option=false

@hide_option=false

You can deside if you want to add hide/show messages to final text file.  

@encoding= tag allows to change encoding of input and output. As default Cp1251 was used (better try UTF-8). 

So, if you don't want to use hide/show commands for specific character you can use none instead of second argument: Sam:;none;s

Specifications:
=
If You have any problems with this software, try to install JDK 19
