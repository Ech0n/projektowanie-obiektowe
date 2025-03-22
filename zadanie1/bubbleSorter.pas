unit BubbleSorter;

interface

procedure generateRandomNumbers(var arrayToFill: array of Integer; arraySize, min, max : Integer);
procedure bubbleSort(var arrayToSort: array of Integer; arraySize : Integer);

implementation

procedure generateRandomNumbers(var arrayToFill: array of Integer;arraySize, min, max : Integer);
var
  i: Integer;
begin
  Randomize;
  for i := 0 to arraySize - 1 do
    arrayToFill[i] := Random(max-min) + min;
end;

procedure bubbleSort(var arrayToSort: array of Integer; arraySize : Integer);
var
  i, j: Integer;
begin
  Randomize;
  for i := 0 to arraySize - 2 do
    for j := 0 to arraySize - i - 2  do
      if arrayToSort[j]>arrayToSort[j+1] then
      begin
        arrayToSort[j] := arrayToSort[j] + arrayToSort[j+1];
        arrayToSort[j+1] := arrayToSort[j] - arrayToSort[j+1];
        arrayToSort[j] := arrayToSort[j] - arrayToSort[j+1];
      end;
end;

end.