#!/bin/bash

## Example
## https://keygenqt.com/download/huge_825_items.json

## max count items
MAX_COUNT=10000 
## max file size in MB
MAX_SIZE=500 
## file name output
FILE_NAME="huge.json"
## array for jpg image from dir to base64
IMAGES=()

## read images
while IFS= read -r line; do
    IMAGES+=("$(base64 -w 0 "$line")")
done <<< $(ls | grep ".*\.jpg$")

## create json start
echo "[" > "$FILE_NAME"

## for json items
for (( i=1; i<=$MAX_COUNT; i++ ))
do

## check file size
filesize=$(ls -s --block-size=1000000 "$FILE_NAME" | cut -d' ' -f1)

echo "Progress: filesize: $filesize, i: $i"

if [ "$filesize" -gt "$MAX_SIZE" ]
then
    break
fi

IMAGE_BASE64=${IMAGES[ $RANDOM % ${#IMAGES[@]} ]}

echo "  {
    \"fname\": \"Виталий\",
    \"lname\": \"Пупкин $i\",
    \"photo\": \"data:image/jpeg;base64,$IMAGE_BASE64\"
  }," >> "$FILE_NAME"
  
done

## end array item
echo "  {
    \"fname\": \"Виталий\",
    \"lname\": \"Пупкин $(( $MAX_COUNT + 1 ))\",
    \"photo\": null
  }" >> "$FILE_NAME"

## close array json
echo "]" >> "$FILE_NAME"

