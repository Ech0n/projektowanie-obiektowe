program Hello;
uses bubbleSorter;

const
  TEST_COUNT = 5;
  TEST_ARRAY_SIZE = 50;
var
  completedTests : Integer = 0;

procedure printArray(var arrayToPrint: array of Integer; arraySize : Integer);
var
  i : Integer;
begin
  write('[');

  for i := 0 to arraySize - 1 do
  begin
    write(arrayToPrint[i]);
    if i <> arraySize - 1 then
      write(', ');
  end;
  writeln('] ');

end;

procedure fillArray(var arrayToFill: array of Integer; arraySize, value: Integer);
var
  i: Integer;
begin
  for i := 0 to arraySize - 1 do
  begin
    arrayToFill[i] := value;
  end;
end;

function compareArrays(var lhs, rhs: array of Integer; arraySize : Integer): Boolean;
var
  i: Integer;
begin
  compareArrays := True;
  for i := 0 to arraySize - 1 do
  begin
    if lhs[i] <> rhs[i] then
      compareArrays := False;
  end;
end;

procedure test_generateRandomNumbersShouldFillAll50Numbers();
var
  testedArray: array[0..TEST_ARRAY_SIZE - 1] of Integer;
  testReult: Boolean;
  i : Integer;
begin
  testReult := True;
  fillArray(testedArray, TEST_ARRAY_SIZE, -1);

  generateRandomNumbers(testedArray, TEST_ARRAY_SIZE, 0 , 100);

  for i := 0 to TEST_ARRAY_SIZE - 1 do
  begin
    if testedArray[i] = -1 then
    begin
      testReult := False;
      Break;
    end;
  end;

  if testReult then
  begin
    writeln('+ test_generateRandomNumbersShouldFillAll50Numbers passed');
    completedTests := completedTests + 1;
  end
  else
  begin
    writeln('- test_generateRandomNumbersShouldFillAll50Numbers failed');
    writeln('    unexpected value found at index: ',i);
    writeln('    array:');
    printArray(testedArray, TEST_ARRAY_SIZE);
  end;
end;

procedure test_generateRandomNumbersAllGeneratedNumbersShouldBeInRange();
var
  testedArray: array[0..100 - 1] of Integer;
  testReult: Boolean;
  i : Integer;
begin
  testReult := True;
  fillArray(testedArray, 100, 0);

  generateRandomNumbers(testedArray, 100, 5, 7);

  for i := 0 to 100 - 1 do
  begin
    if (testedArray[i] < 5) or (testedArray[i] > 6) then
    begin
      testReult := False;
      Break;
    end;
  end;

  if testReult then
  begin
    writeln('+ test_generateRandomNumbersAllGeneratedNumbersShouldBeInRange passed');
    completedTests := completedTests + 1;
  end
  else
  begin
    writeln('- test_generateRandomNumbersAllGeneratedNumbersShouldBeInRange failed');
    writeln('    unexpected value found at index: ',i);
    writeln('    generated array:');
    printArray(testedArray, 100);
  end;
end;

procedure test_generateRandomNumbersShouldHandleNegativeRanges();
var
  testedArray: array[0..TEST_ARRAY_SIZE - 1] of Integer;
  testReult: Boolean;
  i : Integer;
begin
  testReult := True;
  fillArray(testedArray, TEST_ARRAY_SIZE, 0);

  generateRandomNumbers(testedArray, TEST_ARRAY_SIZE, -3, -1);

  for i := 0 to TEST_ARRAY_SIZE - 1 do
  begin
    if (testedArray[i] < -3) or (testedArray[i] > -2) then
    begin
      testReult := False;
      Break;
    end;
  end;

  if testReult then
  begin
    writeln('+ test_generateRandomNumbersShouldHandleRangesStartingInNegatives passed');
    completedTests := completedTests + 1;
  end
  else
  begin
    writeln('- test_generateRandomNumbersShouldHandleRangesStartingInNegatives failed');
    writeln('    unexpected value found at index: ',i);
    writeln('    generated array:');
    printArray(testedArray, TEST_ARRAY_SIZE);
  end;
end;


procedure test_bubbleSortShouldSortAll50Numbers();
const
  referenceArray:array[0..TEST_ARRAY_SIZE - 1] of Integer = (0,1,3,10,15,15,18,22,23,29,30,32,33,33,36,40,41,41,43,44,48,48,49,50,51,51,54,54,54,55,57,59,61,63,65,65,68,76,76,77,80,80,88,88,89,92,93,94,96,98 );
var
  testedArray:array[0..TEST_ARRAY_SIZE - 1] of Integer = (44, 68, 49, 93, 57, 50, 77, 54, 43, 59, 0, 29, 65, 48, 92, 76, 15, 61, 94, 1, 41, 54, 15, 88, 10, 48, 36, 41, 89, 33, 98, 65, 23, 30, 22, 51, 3, 96, 80, 32, 18, 63, 55, 88, 54, 80, 40, 51, 76, 33);
begin
  bubbleSort(testedArray, TEST_ARRAY_SIZE);

  if compareArrays(referenceArray, testedArray, TEST_ARRAY_SIZE) then
  begin
    writeln('+ test_bubbleSortShouldSortAll50Numbers passed');
    completedTests := completedTests + 1;
  end
  else
  begin
    writeln('- test_bubbleSortShouldSortAll50Numbers failed');
    writeln('    expected result:');
    printArray(referenceArray, TEST_ARRAY_SIZE);
    writeln('    actual result:');
    printArray(testedArray, TEST_ARRAY_SIZE);
  end;
end;

procedure test_bubbleSortShouldNotModifyValuesOutOfRange();
var
  testedArray:array[0..TEST_ARRAY_SIZE] of Integer;
begin
  fillArray(testedArray, TEST_ARRAY_SIZE, 5);
  testedArray[50] := 1;
  bubbleSort(testedArray, TEST_ARRAY_SIZE);

  if testedArray[50] = 1 then
  begin
    writeln('+ test_bubbleSortShouldNotModifyValuesOutOfRangeOf50 passed');
    completedTests := completedTests + 1;
  end
  else
  begin
    writeln('- test_bubbleSortShouldNotModifyValuesOutOfRangeOf50 failed');
    writeln('    expected result:');
    writeln('    element #51 value: ', 1);
    writeln('    actual result:');
    writeln('    element #51 value: ', testedArray[50]);
  end;
end;

begin
  writeln('Running ',TEST_COUNT,' tests:');

  test_generateRandomNumbersShouldFillAll50Numbers();
  test_generateRandomNumbersAllGeneratedNumbersShouldBeInRange();
  test_generateRandomNumbersShouldHandleNegativeRanges();
  test_bubbleSortShouldSortAll50Numbers();
  test_bubbleSortShouldNotModifyValuesOutOfRange();

  writeln('Passed ',completedTests,'/',TEST_COUNT);
  writeln('-----------------------------');
  if completedTests <> TEST_COUNT then
  begin
    writeln(TEST_COUNT-completedTests, ' TESTS FAILED!!!');
  end
  else
  begin
    writeln('ALL TESTS PASSED SUCESSFULLY!');
  end;
  writeln('-----------------------------');
end.


