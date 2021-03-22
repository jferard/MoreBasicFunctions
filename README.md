MoreBasicFunctions - A LibreOffice Add-On written in Java that provides more basic functions.
Copyright (C) 2021 Julien Férard.

# Goal
LibreOffice Basic is pretty limited, and calling Calc functions is cumbersome. Hence the idea of this add-on.

# Installation
The preparation step requires the LibreOffice SDK and the procyon decompiler. On Ubuntu

    $ sudo apt-get install libreoffice-dev
    $ sudo apt-get install procyon-decompiler

Now run:

    $ python3 mbfs.py

If something goes wrong, you should check the configuration: https://api.libreoffice.org/docs/install.html.




# Usage
At this time, this is only a proof of concept. You can install the add-on with the extension manager. Then try in the LibreOffice macro console:

    strings = createUNOService("com.github.jferard.mbfs.Strings")
    MsgBox strings.reversed("MoreBasicFunctions")

# Target
MoreBasicFunctions is meant to provide several services.

## `Tests`
Some Junit like functions: `assertTrue`, `assertFalse`, `assertEquals`, `assertNotEquals`,
`assertArrayEquals`, `assertArrayNotEquals`.

## `Strings`
For string manipulation: `reversed`, `split`, `join`, `lower`/`upper` case, `indexOf`... 

TODO: `substring`, maybe `diff`, encodings...

## `Regexes`
For regex usage.

TODO: similar to `Strings`.

## `Sequences`
For sequences manipulation.

TODO: `sort`, `shuffle`, split into chunks, `min`, `max`, `avg`...

## `Math`
TODO.

## `Dates`
TODO: 

## `Paths`
TODO: `glob`, `rglob`.

