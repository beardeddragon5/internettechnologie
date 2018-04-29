#!/bin/bash

figlet 'Aufgabe 1a'

i=0
function tel() {
  echo -ne "$4"
  echo -ne "$4" | nc $1 $2 | tee request$i.html | head -n $3
  i=$(($i+1))
}

tel httpbin.org 80 10 \
"GET / HTTP/1.1
User-Agent: Mozilla/5.0
Accept-Charset: utf-8
Host: httpbin.org
Connection: close

"

figlet 'Aufgabe 1b'

tel httpbin.org 80 100 \
"POST /post HTTP/1.1
Content-Type: application/x-www-form-urlencoded
Content-Length: 28
Connection: close
Host: httpbin.org

organisation=HawLA&name=MaRa

"

figlet 'Aufgabe 2'
echo ''
echo 'well-formed: https://moodle.haw-landshut.de/pluginfile.php/138027/course/section/39531/Internettechnologien%20%28Khelil%29%202018.pdf#page=52'
echo ''

xmllint --dtdvalid vlml.dtd aufgabe2.xml

figlet 'Aufgabe 2e'

xmllint --dtdvalid vlml2.dtd aufgabe2e.xml

figlet 'Aufgabe 3'

xmllint --dtdvalid sml.dtd aufgabe3.xml
