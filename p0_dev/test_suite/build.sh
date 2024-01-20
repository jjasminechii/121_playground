echo "Cleaning .class file in /json"
rm -f json/*.class
echo "Compiling json/JSONType.java"
javac json/JSONType.java
echo "Compiling json/JSONInt.java"
javac json/JSONInt.java
echo "Compiling json/JSONString.java"
javac json/JSONString.java
echo "Compiling json/JSONBool.java"
javac json/JSONBool.java
echo "Compiling json/JSONArray.java"
javac json/JSONArray.java
echo "Compiling json/JSONobject.java"
javac json/JSONObject.java
echo "Compiling json/JSONTestunit.java"
javac json/JSONTestUnit.java
echo "Compiling json/JSONTestCollection.java"
javac json/JSONTestCollection.java
echo "Cleaning .class file in current directory"
rm -f *.class
echo "Compiling Scanner.java"
javac Scanner.java
echo "Compiling Test.java"
javac Test.java
echo "Compiling TestTools.java"
javac TestTools.java
