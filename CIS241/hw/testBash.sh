#! /bin/bash

person=Zach

echo "First case:"
echo $person
echo "Second case:"
echo '$person'
echo "Third case:"
echo "$person"
echo "Fourth case:"
bash
echo $person
echo "Fifth case:"
export my_var="Question 2"
exit
echo $my_var
