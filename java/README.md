# cs303-labs
The labs for my Algorithms and Data Structures course. Written in Java 8.

## Installation
Verify that you have the <a href="http://bfy.tw/G2N8">Java Development Kit</a> installed. Either Oracle Java or OpenJDK8 will work.

Clone the repository with
```bash
git clone https://github.com/cfalcione/cs303-labs.git
```

Then if you're running a Unix-based OS, make sure that the build scripts are executable with
```bash
cd cs303-labs
chmod +x compile.sh run.sh
```
If you're running Windows, the java commands inside the scripts should work fine. Just make sure that the `<project_root>\out\production` folder exists.

## Usage
If you want to run a particular lab, change the getCurrentLab() method in src/cfalcione/cs330/Main.java to that lab. For example:
```java
private static Lab getCurrentLab() {
    return new Lab04();
}
```
and then compile and run with
```bash
./run.sh
```

The stdout of some labs (namely the Sorting Algorithm ones) are formatted as csv's. To get a csv file, use
```bash
./run.sh > output.csv
```
on Unix-based systems. On Windows, append ` > output.csv` to whatever command you're using to run the files. For example:
```batch
cd out/production
java cfalcione.cs303.Main > output.csv
```
