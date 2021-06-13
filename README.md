MoreBasicFunctions - A LibreOffice Add-On written in Java that provides more basic functions.
Copyright (C) 2021 Julien Férard.

Questions: https://github.com/jferard/MoreBasicFunctions/discussions

Bug reports: https://github.com/jferard/MoreBasicFunctions/issues

# Goal
LibreOffice Basic is pretty limited, and calling Calc functions is cumbersome. Hence the idea of this add-on. 

**There is a major limitation: the services won't be available unless the add-on is installed. That means that your scripts won't be usable on a standard LibreOffice installation.**

Use cases:

* One PC script (typically personal script): install the add-on on the PC and use the services;
* Prototype: the add-on will help you to keep the code clean;
* Developer: the `Tests` service is mandatory on the developer PC but not on the end user PC.

# Rationale
There are already dozens of functions available via the [`com.sun.star.sheet.FunctionAccess`](https://api.libreoffice.org/docs/idl/ref/servicecom_1_1sun_1_1star_1_1sheet_1_1FunctionAccess.html) service, but the syntax is tedious:

	functionAccess = createUnoService("com.sun.star.sheet.FunctionAccess")  
	MsgBox functionAccess.callFunction("SUBSTITUTE", Array("123123123", "3", "abc"))
	
Versus:

	Strings = createUnoService("com.github.jferard.mbfs.Strings")
	MsgBox Strings.replace("123123123", "3", "abc")

# Installation
Download the latest [release](https://github.com/jferard/MoreBasicFunctions/releases) or compile the extension (see Compilation below).

In LibreOffice: first, save any opened document.
Then `Tools > Extension Manager...` and choose the MoreBasicFunctions extension. Accept the license and close the extension manager. Be sure to restart LibreOffice.

# Compilation
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

See the [doc directory](https://github.com/jferard/MoreBasicFunctions/tree/main/doc) for detailed information.

## `Tests`
Some Junit like functions: `assertTrue`, `assertFalse`, `assertEquals`, `assertNotEquals`,
`assertArrayEquals`, `assertArrayNotEquals`.

## `Strings`
For string manipulation: `reversed`, `split`, `join`, `lower`/`upper` case, `indexOf`, 
`substring`...

TODO: maybe `diff`, encodings...

## `Characters`
For character manipulation `Ord`/`Chr` and identification: `isLetter`, `isDigit`...

## `Regexes`
For regex usage: `match`, `findAll`/`findFirst`, `replaceAll`/`replaceFirst`.  

## `Collections`
For collections manipulation: `sort`, `shuffle`, `min`, `max`, `avg`, `concat`, `flatten`, `times`, 
`chunks`, `randomElement`, `contains`.

## `Dates`
For date and time manipulation: `now`, `strptime`/`strftime`...

TODO: Add one day, one month...

## `Paths`
For path manipulation: `list`, `recursiveList`, `split`/`join`, `parent`, `withExtension`...

## `Math`
TODO.

# About Basic-Java mappings
The [Developer's guide](https://wiki.openoffice.org/w/images/d/d9/DevelopersGuide_OOo3.1.0.pdf) 
give an overview of the mappings between UNO, Java, C++ and Basic (p. 72). But this table does not
show how Basic values will be typed in Java.

The table aims to be more complete:

| Basic Value                                                               | Java Type           |
| ------------------------------------------------------------------------- | ------------------- |
| `Boolean`                                                                 | `java.lang.Boolean` | 
| `Single`                                                                  | `java.lang.Float`   | 
| `Double`                                                                  | `java.lang.Double`  | 
| `Integer`                                                                 | `java.lang.Short`   | 
| `Long`                                                                    | `java.lang.Integer` | 
| literal boolean (`True`)                                                  | `java.lang.Boolean` | 
| literal floating point value (`3.14`)                                     | `java.lang.Double`  | 
| literal number -128 <= x < 128                                            | `java.lang.Byte`    | 
| literal number -32768 <= x < -128 or 128 <= x < 32768                     | `java.lang.Short`   | 
| literal number -2147483648 <= x < -32768 or 32768 < x < -2147483648       | `java.lang.Integer` | 
| larger literal number                                                     | `java.lang.Double`  | 

Arrays are either mapped to primitive arrays (if the values are consistent) or to object arrays.

| Basic Value                                                               | Java Type           |
| ------------------------------------------------------------------------- | ------------------- |
| array of `Boolean`s or boolean values                                     | `boolean[]`         | 
| array of `Single`s or float values                                        | `float[]`           | 
| array of `Double`s or double values                                       | `double[]`          | 
| array of `Integer`s or integer values                                     | `short[]`           | 
| array of `Long`s or long values                                           | `integer[]`         | 
| array of mixed types                                                      | `Object[]`          |

In the case of a mixed array, the object are typed according to the first table.

