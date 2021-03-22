MoreBasicFunctions - A LibreOffice Add-On written in Java that provides more basic functions.
Copyright (C) 2021 Julien Férard.

# Goal
LibreOffice Basic is pretty limited, and calling Calc functions is cumbersome. Hence the idea of this add-on. 

# Rationale
There are already dozens of functions available via the [`com.sun.star.sheet.FunctionAccess`](https://api.libreoffice.org/docs/idl/ref/servicecom_1_1sun_1_1star_1_1sheet_1_1FunctionAccess.html) service, but the syntax is tedious:

	functionAccess = createUnoService("com.sun.star.sheet.FunctionAccess")  
	MsgBox functionAccess.callFunction("SUBSTITUTE", Array("123123123", "3", "abc"))
	
Versus:

	Strings = createUnoService("com.github.jferard.mbfs.Strings")
	MsgBox Strings.replace("123123123", "3", "abc")

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

