# Java Interview ch04



# 리스트 정렬하기

* 자바는 정렬을 돕기위해 `Comparable` 과 `Comparator`라는 두 가지 인터페이스를 제공한다.
* 두 인터페이스는 모두 public 접근 변경자로 선언하기 때문에, 모든 용도의 자료를 담을 수 있다.
* `Comparable` 인터페이스는 자연스러운 순서로 정렬할 때 사용한다.
* `Comparator` 인터페이스는 원하는 대로 정렬 순서를 지정하고 싶은 곳에 사용한다.
* 배열을 정렬할 때에는 일반적으로 `Array`나 `Collection` 클래스에 내장된 라이브러리를 사용한다.
* `Array`와 `Collection` 클래스는 몇가지 오버로딩된 정렬 메서드를 갖고있는데, 크게 배열을 매개변수로 받는 메서드, 배열과 `Comparator` 객체를 매개변수로 받는 메서드 두가지로 분류된다.
* 그리고 각각의 원시 타입`primitive type`과 참조`reference type`에 관한 객체를 오버로드한다.

* `Comparator` 객체가 없는 구현체는 타입을 자연스런 순서로 정렬한다. 아래 코드는 작은 수 부터 큰수를 정렬한  int타입의 배열 예다.

  *int 타입 배열을 순서대로 정렬하기*

  ```java
  //Test
  	public void sortInts() {
  	final int[] numbers = {-3, -5, 1, 7, 4, -2};
  	final int[] expected = {-5, -3, -2, 1, 4, 7};
  		
  	Arrays.sort(numbers);
  	Assert.assertArrayEquals(expected, numbers); // true
  }
  ```



* 객체 정렬하려면, 아래 코드 처럼 `Comparable` 인터페이스를 구현해야한다.

  *객체를 순서대로 정렬하기*

  ```java
  public void sortObjects() {
  		final String[] strings = {"z", "x", "y", "abc", "zzz", "zezzy"};
  		final String[] expected = {"abc", "x", "y", "z", "zezzy", "zzz"};
  	
  		Arrays.sort(strings);
  		assertArrayEquals(expected, strings); //true
  	
  	}
  ```

* `String`클래스는 `Comparable` 인터페이스를 구현하기 때문에 예상한 대로 정렬이 된다.

* `Comparable`인터페이스를 구현하는 것들은 `String`, `Integer`, `Data`, `File`등과 같은 것들이다.

* 그리고 이것들은 기본적으로 오름차순 형태로 구현이 되도록 만들어져 있다.

* 정렬해야 하는 타입이 `Comparable` 인터페이스를 구현하지 않는다면, `ClassCastException`예외가 발생한다.

* 아래의 코드는 `Comparable` 인터페이스를 구현하지 않을 때 발생하는 일이다.

  *Comparable 인터페이스 없이 정렬하기*

  ```java
  private static class NotComparable { // 객체 생성
  		private int i;
  
  		private NotComparable(final int i) {
  			this.i = i;
  		}
  	}
  
  	@Test
  	public void sortNotComparable() {
  		final List<NotComparable> objects = new ArrayList<>();
  		for (int i = 0; i < 10; i++) {
  			objects.add(new NotComparable(i));
  		}
  
  		try {
  			Arrays.sort(objects.toArray()); 
  		} catch (Exception e) {
  			// 올바른 동작 - 정렬할 수 없다.
  			return;
  		}
  
  		fail();
  	}
  ```

* 왜 위의 코드에서는 Array.sort를 사용할 수 없을까?

* 이유는 정렬을 시도했지만, 객체내의 어떤 변수를 어떤 기준으로 정렬할지 정하지 않았기 때문이다.

* 즉, `String`타입의 배열이나 `ArrayList`는 값이 하나지만, 객체를 정렬할 경우 객체내의 어떤 변수로 정렬할지 만들어 줘야만 한다.

* 정렬 기준을 명시하기 위한 방법은 2가지가 있다.
  * `Comparable` 인터페이스 구현
  * `Comparator` 구현



1. Comparable 인터페이스 구현하여 정렬

* 컴파일러는 매개변수의 자료형 Comparable 인터페이스를 구현했다고 생각하므로, Collection.sort 메서드를 사용할 수 없다. 

* 클래스에 `Comparable<T>` 라는 인터페이스를 `implements` 해준다.

* 이후에 `Comparable`인터페이스에 있는 `compareTo`라는 메서드를 오버라이드 해줘야한다.

* `compareTo` 메서드는 다음과 같은 규칙으로 작성한다.

* 두 번째 매개변수 앞에 첫 번째 매개변수가 오면 음수 값, 두 개의 매개변수가 같으면 0, 두 번째 매개변수가 첫 번째 매개변수보다 앞에 오면 양수값을 반환한다.

* 메서드 시그니처

  ```java
  public static <T extends Comparable<? super T>> void sort(List<T> list)
  ```


2. Comparator 구현

* 지정한 순서대로 정렬하고 싶다면, `sort`메서드에서 사용할 `Comparator`인터페이스를 구현한 후 이를 `sort`메서드에 제공해야한다.
* 이를 위해 `Comparator`인터페이스에는 자료형 T를 구현하는 `int compare(T o1, T o2)`와 `boolean equals(Objects o)` 라는 두 가지 메서드가 있다. 
* `compare` 메서드는 양수, 0, 음수 이라는 세 가지 상태 중 하나로 `int`타입의 값을 반환한다.
* 구체적으로 두 번째 매개변수 앞에 첫 번째 매개변수가 오면 음수 값, 두 개의 매개변수가 같으면 0, 두 번째 매개변수가 첫 번째 매개변수보다 앞에 오면 양수값을 반환한다.

* `Collections.sort`메서드는 두번째 인자로 `Comprator` 인터페이스를 받을 수 있도록 해놓았습니다.

* `Comparator` 인터페이스의 `compare` 메서드를 오버라이드 하면 됩니다.
* `compare`메서드는 `Comparable`의 `compareTo `메서드와 동일한 규칙으로 작성하면 된다.

* `Comparator` 인터페이스를 통해서, 역순으로 정렬하고 싶다면 다음과 같이 구현하면 된다.

  *Comparator 인터페이스를 사용하여 숫자를 역순으로 정렬하기*

  ```java
  public class ReverseNumericalOrder implements Comparator<Integer>{
      @Override
      public int compare(Integer o1, Integer o2){
          return o2-o1;
      }
      // 같은 부분은 생략하도록 한다.
  }
  ```

  *Comparator 인터페이스를 적용하여 숫자를 역순으로 정렬하기*

  ```java
  @Test
  public void customSort(){
      final List<Integer> numbers = Arrays.asList(4, 7, 1, 6, 3, 5, 4);
      final List<Integer> expected = Arrays.asList(7, 6, 5, 4, 4, 3, 1);    
      Collections.sort(numbers, new ReverseNumericalOrder());
      assertEquals(expected, numbers);
  }
  ```


# 정렬 알고리즘

![](https://media.giphy.com/media/xpySy9v6mSxX2/giphy.gif)



## 1. 버블 정렬 알고리즘

* 버블 정렬은 바로 다음의 수와 크기를 비교하여, 다음 것보다 크다면 자리를 바꿔주는 것.
* 다음 과 같이 구현한다.

```java

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class BubbleSort {

public void bubbleSort(int[] numbers) {
    boolean numbersSwitched;
    do {
        numbersSwitched = false;
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i + 1] < numbers[i]) {
                int tmp = numbers[i + 1];
                numbers[i + 1] = numbers[i];
                numbers[i] = tmp;
                numbersSwitched = true;
            }
        }
    } while (numbersSwitched);
}

    @Test
    public void testBubble() {
        final int[] numbers = {6, 4, 9, 5};
        final int[] expected = {4, 5, 6, 9};

        bubbleSort(numbers);
        assertArrayEquals(expected, numbers);
    }
}
```

* 구현하기는 간단하지만, 정말 비효율적이다.
* 최악의 경우 O(n^2)의 성능을 내는데, 이는 순환할때마다, 하나의 원소만 바꿔주기 때문이다.
* 최선의 경우는 이미 리스트가 정렬되어 있을 경우이다. 원소의 위치가 변경되지 않으므로, 원소의 위치를 변경하지않아도 된다. 이때는 O(n)의 성능을 갖는다.



## 2. 삽입 정렬알고리즘

* 버블 정렬과는 다르게 정렬된 원소들을 새로운 리스트에 담아서 반환한다.

* 즉, 새로운 리스트를 생성하고, 그 리스트를 반환하는 것.

* 다음과 같이 구현한다.

  ```java
  import org.junit.Test;
  
  import java.util.Arrays;
  import java.util.LinkedList;
  import java.util.List;
  
  import static junit.framework.Assert.assertEquals;
  
  public static List<Integer> insertSort(final List<Integer> numbers) {
      final List<Integer> sortedList = new LinkedList<>();
      
      originalList: for(Integer number : numbers){
          for(int i = 0; i < sortedList.size(); i++){
              if(number < sortedList.get(i)){
                  sortedList.add(i, number);
                  continue originalList;
              }
          }
          sortedList.add(sortedList.size(), number);
      }
  }
  ```

* 삽입정렬에서 반환하는 리스트는 `LinkedList` 클래스의 인스턴스이다.

* 연결 리스트는 리스트의 중간에 원소를 추가하는데 아주 효율적이다. 리스트의 노드들에 포인터를 재배열하는 작업을 간단하게 하기 때문.

* 오히려 `ArrayList`클래스를 사용했다면, 리스트 중간에 원소를 추가할 경우 처리 속도가 많이 느렸을 것이다. 이는 배열을 이용하므로 리스트의 앞이나 중간에 원소를 삽입하면 그 뒤에 있는 모든 원소를 한 칸씩 뒤로 이동시켜야 하기 떄문에, 배열의 크기가 커지면 이런 작업을 하는 데 부담이 갈 것이다.

* 마지막으로 `originalList:` 라고 적은 부분은 다음과 같이 작동한다.

  * 원소가 적절한 위치에 삽입될 때 바깥쪽 반목문의 조건을 이용해, `continue`문을 호출할 수 있으며, 알고리즘은 다음 원소로 이동한다.

* 이 부분이 버블 정렬보다 좋은 점이다. 반환할 리스트가 생성이 되면 바로 반환을 할 수 있다는 것.

* 물론 최악의 경우 시간복잡도는 여전히 O(n^2)일 것이다... 이미 정렬된 리스트를 다시 정렬하는 경우에는 매번 원소를 삽입할 때마다 새 리스트의 끝까지 반복문을 실행해야 하기 때문.

* 반대로 역으로 정렬된 것을 다시 정렬할때는 O(n). 



## 3. 퀵 정렬 알고리즘

* 이 알고리즘은 재귀적이다. 기본적으로 0 혹은 1이라는 원소를 제공한다.
* 리스트는 이미 정렬되었으므로, 간단하게 반환할 수 있다.

*QuickSort.java*

```java
public static List<Integer> quickSort(List<Integer> numbers){
    if(numbers.size() < 2) return numbers;
    
    final Integer pivot = numbers.get(0);
    final List<Integer> lower = new ArrayList<>();
    final List<Integer> higher = new ArrayList<>();
    
    for(int i = 1; i < numbers.size(); i++){
        if(numbers.get(i) < pivot){
            lower.add(numbers.get(i));
        } else {
            higher.add(numbers.get(i));
        }
    }
    
    final List<Integer> sorted = quickSort(lower);
    sorted.add(pivot);
    sorted.addAll(quickSort(higher));
    
    return sorted;
}
```

* 재귀 알고리즘을 만들 때 주의해야 할 것은 실행을 종료할 수 있는지 확인해야한다.
* 리스트에서 `pivot`이라는 임시 원소를 하나 선택한다. 
* 위의 코드에서는 1번째 원소를 사용하지만 어떤 것을 사용해도 상관없다.
* 남아있는 원소들은 pivot보다 크거나 같은 것들과 작은 것의 두 가지로 구분된다.
* 그리고 각 리스트는 다시 스스로를 정렬하는 메서드로 호출한다.
* 이 코드의 시간복잡도는 평균 O(n log n) 이다.
* 그러나 최악의 경우는 역시 O(n^2)
* pivot을 어떻게 선택하느냐에 따라 다를 수 있기 때문이다.



## 4. 병합 정렬 알고리즘

* 이것은 또 다른 분할 정복 알고리즘이다.
* 리스트를 두 개로 나누고 각 하위 리스트를 정렬한 후에 각각을 하나로 합친다.

* 슈도 코드로 나타낸다면 다음과 같다.

  ```pseudocode
  method mergesort(list l):
  	if list.size<2:
  		return 1
  
      let middleIdx = l.size/2
      let leftList = elements between l(0) and l(middleIdx - 1)
      let rightList = elements between l(middleIdx) and l(size - 1)
  
      let sortedLeft = mergesort(leftList)
      let sortedRight = mergesort(rightList)
  
      return merge(sortedLeft, sortedRight)
  
  method merge(list l, list r):
  	let leftPtr = 0
  	let rightPtr = 0
  	let toReturn = new return
  
      while(leftPtr < l.size and rightPtr < r.size):
          if (l(leftPtr) < r(rightPtr)):
              toReturn.add(l(leftPtr))
              leftPtr++
          else:
              toReturn.add(r(rightPtr)):
              rightPtr++
  
      while(leftPtr < l.size):
      	toReturn.add(l(leftPtr))
      	leftPtr++
      	
      while(RightPtr < r.size):
      	toReturn.add(r(Rightsize))
      	rightPtr++
      	
      return toReturn
  ```

* 주 코드`method merge()`는 두개의 리스트를 효율적으로 병합한다. 의사코드는 각각의 하위 리스트에 대한 포인터가 있고, 포인터는 가장 작은 값을 추가한 뒤 적절하게 해당 포인터를 증가시킨다.

* 일단 포인터 하나가 리스트의 마지막에 도달하면 다른 리스트에 남아있는 모든 원소를 추가할 수 있다.

* 앞부분 `merge`메서드에 있는 두 번째, 세 번째 `while`문에서 하나의 `while`문은 바로 `false`를 반환한다. 모든 원소가 첫번재 `while`문에서 하위리스트로 사용되었기 때문.

* 자 위의 슈도코드를 이제 자바코드로 바꿔보자

  *mergeSort.java*

  ```java
  import org.junit.Test;
  
  import java.util.ArrayList;
  import java.util.Arrays;
  import java.util.List;
  
  import static junit.framework.Assert.assertEquals;
  
  public class Mergesort {
  
      public static List<Integer> mergesort(final List<Integer> values) {
          if (values.size() < 2) {
              return values;
          }
  
          final List<Integer> leftHalf =
                  values.subList(0, values.size() / 2);
          final List<Integer> rightHalf =
                  values.subList(values.size() / 2, values.size());
  
          return merge(mergesort(leftHalf), mergesort(rightHalf));
      }
  
      private static List<Integer> merge(final List<Integer> left,
                                         final List<Integer> right) {
          int leftPtr = 0;
          int rightPtr = 0;
  
          final List<Integer> merged =
                  new ArrayList<>(left.size() + right.size());
  
          while (leftPtr < left.size() && rightPtr < right.size()) {
              if (left.get(leftPtr) < right.get(rightPtr)) {
                  merged.add(left.get(leftPtr));
                  leftPtr++;
              } else {
                  merged.add(right.get(rightPtr));
                  rightPtr++;
              }
          }
  
          while (leftPtr < left.size()) {
              merged.add(left.get(leftPtr));
              leftPtr++;
          }
  
          while (rightPtr < right.size()) {
              merged.add(right.get(rightPtr));
              rightPtr++;
          }
  
          return merged;
      }
  
      @Test
      public void testMergesort() {
          final List<Integer> numbers = Arrays.asList(6, 4, 9, 5);
          final List<Integer> expected = Arrays.asList(4, 5, 6, 9);
  
          final List<Integer> actual = mergesort(numbers);
          assertEquals(expected, actual);
      }
  }
  ```

* 슈도 코드와 매우 흡사하다.

* List 인터페이스의 `sublist`메서드는 `fromIndex`와 `toIndex`라는 매개변수를 두개 받는다.

  * `sublist(int fromIndex, int toIndex)`

* `fromIndex`는 자기 자신을 포함하지만, `toIndex`는 자기 자신을 포함하지 않는다.
* 병합 정렬의 성능은 O(n log n)이고 각각의 병합 시간은 O(n)이며, 각 재귀호출은 주어진 리스트 숫자의 절반만큼만 발생한다는 것을 기억하자.



## 리스트 검색하기

*이진 검색은 어떻게 구현하는가?*

* 리스트가 정렬되어있거나, 검색하기 전에 정렬을 수행한 상황에서 값을 찾을 땐 이진 검색을 사용하는 것이 효율적인 방법이다.

* 이 알고리즘은 이미 정렬된 리스트의 성질을 이용한다. 

* 즉, 많은 원소가 주어진 원소와 맞지 않다는  걸 이미 알고 사용하는 것이므로, 원소를 일일이 비교해보지도 않고 주어진 원소를 찾을 수 있다.

* 만약 백만 개의 원소가 있다면 20번 미만의 비교로도 주어진 원소를 찾을 수 있다.

* 그러므로 이 알고리즘의 시간복잡도는 O(n log n)이다.

* 자바로 다음과 같이 구현한다.

  *binarySearch.java*

  ```java
  public static boolean binarySearch(final List<Integer> numbers, final Integer value){
      if(numbers == null || numbers.isEmpty()){
          return false;
      }
      
      final Integer comparison = numbers.get(numbers.size() / 2);
      if(value.equals(comparison)){
          return true;
      }
      
      if(value < comparison){
          return binarySearch(numbers.subList(0, numbers.size()/ 2), value);
      } else {
          return binarySearch(numbers.subList(numbers.size() / 2 + 1, numbers.size()), value);
      }
  }
  ```



## 요약

* 재귀 알고리즘의 구현 방법을 확실히 이해하도록 하자. 
* 호출 스택의 단계가 너무 깊어지면, `StackOverflowException`예외가 발생할 수 있으니 주의하자.
* 면접상황이 아니라면 그냥 라이브러리를 사용하자
* 훨씬 잘 구현되어있으니까..
* 작은 리스트라면 삽입정렬을 사용하지만, 리스트의 크기가 지정된 크기를 넘어가면 병합 정렬ㄹ을 사용한다.

