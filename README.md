Neptus, Command and Control Framework
=====================================

I worked on this project from 2005 to 2015, at LSTS FEUP-DEEC, mainly creating command and control consoles for UAVs, AUVs, ASVs and ROVs. These consoles are editable and have a plugin architecture to include panels, gadgets, etc.

[![IMAGE ALT TEXT HERE](https://img.youtube.com/vi/4p_mSsJDwQk/0.jpg)](https://www.youtube.com/watch?v=4p_mSsJDwQk)

Copyright © 2004-2024 Universidade do Porto - Faculdade de Engenharia
Laboratório de Sistemas e Tecnologia Subaquática (LSTS)
All rights reserved.
Rua Dr. Roberto Frias s/n, sala I203, 4200-465 Porto, Portugal


This software is provided using a dual license. One commercial and for that
contact licensing@lsts.pt or lsts@fe.up.pt, and another using a modified
"European Union Public Licence - EUPL v.1.1 Usage" (check the included
[LICENSE.md](LICENSE.md) file).

For developers: please read [javadoc/overview-and-developers-guide.html](javadoc/overview-and-developers-guide.html).

For more information check Neptus web site (http://www.lsts.pt/toolchain/neptus/).

[![Build Status](https://travis-ci.org/LSTS/neptus.svg?branch=develop)](https://travis-ci.org/LSTS/neptus)
[![Java CI with Gradle](https://github.com/LSTS/neptus/workflows/Java%20CI%20with%20Gradle/badge.svg)](https://github.com/LSTS/neptus/actions?query=workflow%3A%22Java+CI+with+Gradle%22)

---------------------------------------------------------------------

You can tweak the launching of the application by creating a file
named "neptus.vmoptions" next to the launcher at the root directory.
In it you can set scaling for hi dpi monitors (only needed on Linux),
or expand the maximum memory to use by the application. Example bellow,
(one argument per line, and '#' means comment out):

```properties
# For 4GB of max memory
-Xmx4g
# For a 200% scaling
-Dsun.java2d.uiScale=2.0
```
