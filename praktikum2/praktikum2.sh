#!/bin/bash

figlet "Aufgabe 1"

xmllint --dtdvalid bpml.dtd aufgabe1.xml

figlet "Aufgabe 2"

xmllint -schema vml.xsd aufgabe2.xml --noout
