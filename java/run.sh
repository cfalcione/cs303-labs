#!/bin/sh
./compile.sh
cd out/production/
java cfalcione.cs303.Main $@
cd ../..
