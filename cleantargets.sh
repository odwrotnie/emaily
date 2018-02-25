find . -name "target" | xargs rm -rf
find . -name "*.class" | xargs rm

rm -rf ~/.sbt/0.13/staging/*
