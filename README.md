MoreBasicFunctions - A LibreOffice Add-On written in Java that provides more basic functions.
Copyright (C) 2021 Julien Férard.

# Goal
LibreOffice Basic is pretty limited, and calling Calc functions is cumbersome. Hence the idea of this add-on.

# Usage
At this time, this is only a proof of concept. You can install the add-on with the extension manager. Then try in the LibreOffice macro console:

    strings = createUNOService("com.github.jferard.mbfs.Strings")
    MsgBox strings.reversed("MoreBasicFunctions")

# Target
MoreBasicFunctions is meant to provide several services.

## `Strings`
For string manipulation: reverse, split, join, lower/upper case, slice, search... 

Maybe diff, encodings...

## `Regexes`
For regex usage: similar to `Strings`.

## `Sequences`
For sequences manipulation: sort, shuffle, split into chunks, min, max, avg...

## `Math`

## `Dates`

## To be continued
