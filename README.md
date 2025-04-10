# 📘 CSE331 - Assignment 1 (25-1)

**20201325 홍대의**  
This repository is for **CSE331: Introduction to Algorithms - Assignment1**

- 데이터셋(Dataset)은 매 실험 시마다 `InputGenerator.java`를 실행하여 크기별로 데이터를 생성하여 코드 내에서 바로 사용되었습니다.
따라서 리포지토리에 데이터셋이 별도로 저장되어 있진 않습니다.  
  

 <br/>

## 📂 Repository Overview

### Algorithm_assignment1
- `InputGenerator.java` : Input array 생성을 위한 코드 (랜덤,정렬,역정렬,부분정렬)
- `Main.java` : InputGenerator를 import해서 input을 return받고 바로 실행
- Result log
    - Random_input : folder contains random input result log png files for each sorting algorithm
    - Sorted_Reversd_Partial_input.txt : text file contains  sorted, reversed, partial input result log for each sorting algorithm
- common
    - `Analresult.java` : 분석 결과를 담을 구조체
    - `Item.java` : stability 판별을 위한 구조체, array는 (item,first_index) 쌍을 가짐
- Algorithms
    - `Sortings.java` : Sorting Algorithms를 위한 추상 클래스, 분석결과 출력 코드 등
    - `BubbleSort.java`
    - `CocktailSort.java`
    - `CombSort.java`
    - `HeapSort.java`
    - `InsertionSort.java`
    - `IntroSort.java`
    - `LibrarySort.java`
    - `MergeSort.java`
    - `QuickSort.java`
    - `QuickSort2.java`
    - `SelectionSort.java`
    - `TimSort.java`
    - `TournamentSort.java`
