# Ch5 _ dataStucture

## 1. List 
* 특정 타입 값들이 순차적으로 정렬된 Collection이다. Java에서는 보통 `LinkedList` 또는 `ArrayList`를 사용한다. 

* ### 배열과 리스트간의 관계
  1) 배열

    * 배열은 대괄호를 이용하는 타입. 정의할 때, 크기를 꼭 정해줘야함.

        ```java
        @Test
        public void arrayDefinitions(){
            final int[] integers = new int[3];
            final boolean[] booleans = {false, true, true, false};
        
            final Random r = new Random();
            final String[] randomArrayLength = new String[r.nextInt(100)];
        }
        ```

     * 배열의 원소에는 인덱스 값을 이용해서 직접 접근이 가능하다. 이것을 우리는 **랜덤접근**이라고 한다.
            
     * 배열 전체를 사용 중일 때, 원소를 추가하려면 배열 크기를 늘려야한다. 실제로는 더 큰 배열을 새롭게 만들고, 현재 배열에 있는 모든 원소를 새로운 배열로 복사한 다음, 새로운 배열이 원본 배열의 주소를 가리키도록 재할당하는 것이다. 
            
     * JVM은 이런 과정에서 원소들을 한꺼번에 복사하도록 하는데, System 클래스 객체의 `arrayCopy`라는 정적 메소드를 사용하여, 배열의 일부나 전부를 새로운 배열로 복사할 수 있도록 한다.

        ```java
        //Test
        public void arrayCopy() {
            int [] integers = {0, 1, 2, 3, 4};
        
            int [] newIntegersArray = new int[integers.length + 1];
            System.arraycopy(integers, 0, newIntegersArray, 0, integers.length);
            integers = newIntegersArray;
            integers[5] = 5;
        
            assertEquals(5, integers[5]);
        }
        ```

     * 정수 타입의 배열에는 `final`키워드를 사용할 수 없다. 이는 값을 재할당해야하기 때문이며, 이 때 배열대신 List 인터페이스를 사용할 수도 있다. `ArrayList`클래스는 리스트의 데이터로 배열을 사용한다는 List 인터페이스의 구현체이다.
            
     * `ArrayList`클래스를 생성할 때는 배열의 초기 크기를 지정할 수 있다. 그러나, 크기를 지정해주지 않는다면, 기본크기는 **10**이다.
            
     * 원소로 가득한 배열에 계속 원소를 추가할 때마다, `ArrayList` 클래스는 자동으로 더 큰 배열에 할당한다. 그러나, 이때 시간이 소요되며 메모리 용량도 더 크게 소모한다. 그러므로 애초에 생성할때 크기를 지정해주는 것이 좋다.
            
     * `ArrayList` 클래스로 생성한 배열의 시작위치, 혹은 중간 위치에 새로운 원소를 추가하려고 하면 다음에 있는 모든 원소는 공간을 만들기 위해서 이동해야만 한다. 이 작업은 꽤 많은 연산량을 소모하는 작업이다.
            
     * 배열 크기 재할당은 항상 **단방향**으로만 이루어 진다는 것을 기억하자
            
     * `ArrayList`로 생성한 배열은 원소를 많이 삭제해도 크기는 줄어들지 않는다. 원소의 개수가 계속 변경되는 리스트라면 `ArrayList` 클래스를 사용하는 것은 좋은 선택이 아닐 수 있다.

  2) 리스트

    * 원소들을 저장하는 데 배열을 사용하지않고, 리스트 안에서 다음 원소를 가리키는 내부 객체를 이용한다.

  ```java
  public class SimpleLinkedList<E>{
      private static class Element<E>{
          E value;
          Element<E> next;
      }
      private Element<E> head;
  }
  ```

  * `LinkedList` 인스턴스는 `Element`라는 리스트의 첫 부분을 가리키는 `head`라는 필드만 참조한다. 
  * `next`는 리스트안에 다음 원소 위치를 가리키는 재귀적 타입인데, 이를 이용하면 리스트 사이를 쉽게 이동할 수 있고, 순서대로 각 원소를 찾아 처리할 수 있다.
  * 리스트에서 인덱스를 이용해 원소에 접근할 경우엔 해당 인덱스에 접근할 때까지 리스트를 순회해야한다.
  * 순회는 물론 단방향으로.
  * 그러나 `ArrayList`에서는 '랜덤접근'을 통해서 해당 인덱스를 바로 찾을 수 있도록 돕는다.

  3) 결론

  * 일반적으로 원소에 랜덤접근할 수 있어야하거나, 리스트 크기가 클수록  `ArrayList` 클래스를 사용하는 것이 좋다.
  * 그러나 리스트 중간중간에 원소를 삽입, 제거해야할 일이 많을 수록 `LinkedList`클래스를 사용하는 것이 더 좋다.  게다가 배열 재할당과정에 필요한 손실을 막아주기에, 리스트 크기가 작아지면 메모리용량 역시 작아진다는 장점도 있다.

* ### Queue 와 Deque

  * Queue는 FIFO구조를 가진 자바 인터페이스이다. 

  * 새 원소를 추가하는 `add`메서드, 오래된 원소를 제거하는 `remove`메서드, 가장 오래된 메서드를 반환하지만, 제거는 하지않는 `peek`메서드가 있다. 

    ```java
    //Test
    public void queueInsertion(){
        final Queue<String> queue = new LinkedList<>();
    	queue.add("First");   
        queue.add("Second");   
        queue.add("Third");
        
        assertEquals("First", queue.remove());
        assertEquals("Second", queue.remove());
        assertEquals("Third", queue.peek());
    }
    ```

  * Deque 는 Queue의 자료구조 확장형이며, 양쪽 끝에서 자료를 추가, 삭제할 수 있다.

## 2. Tree

* 트리는 자식이라 부르는 서로 다은 원소를 많이 나열할 수 있는 자료구조이다.

* 트리의 기본구조는 이진트리로, 이진 트리의 각 원소는 최대 두개의 자식을 갖는다.

* 이진 트리의 구현 중 하나로 이진 검색트리라는 것이 있는 데, 이때 주어진 노드의 값보다 작은 자식은 왼쪽에, 큰 원소는 오른쪽에 위치한다.

  *Tree 구현하기*

  ```java
  public class SimpleTree<E extends Comparable>{
      private E value;
      private SimpleTree<E> left;
      private SimpleTree<E> right;
      ... // 생성자, getter, setter 메서드는 생략
  }
  ```

  *이진 검색 트리에서 값찾기*

  ```java
  public boolean search(final E toFind){
      if(toFind.equals(value)){
          return true;
      }
      
      if(toFind.compareTo(value)<0 && left!=null){
          return left.search(toFind);
      }
      
      return right != null && right.search(toFind);
  }
  ```

  *이진 트리에 값을 삽입하기*

  ```java
  public void insert(final E toInsert){
      if(toInsert.compareTo(value)<0){
          if(left == null){
              left = new SimpleTree<>(toInsert, null, null);
          }else{
              left.insert(toInsert);
          }
      }else{
          if(right == null){
              right = new SimpleTree<>(toInsert, null, null);
          }else{
              right.insert(toInsert);
          }
      }
  }
  ```

  *데이터가 적절한 위치에 삽입 되었는지 확인*

  ```java
  //Test
  public void createTree(){
      final SimpleTree<Integer> root = new SimpleTree<>(7, null, null);
      root.insert(3);
      root.insert(9);
      root.insert(10);
      assertTure(root.search(10));
      assertEquals(Integer.valueOf(10),root.getRight().getRight().getValue());
  }
  ```

* 객체 지향 프로그래밍에서는 Null 객체 패턴을 이용해 메서드를 단순화 할 수 있으며, null 값을 확인하는 과정도 제외시킬 수 있다.

* 이 방법은 값을 가진 노드와 트리의 끝을 표현하는 리프라는 두 가지 타입의 노드로 트리를 구성한다.

  *Null 객체로 트리 구성하기*

  ```java
  public interface Tree<E extends Comparable>{
    boolean search(E toFine);
    void insert(E toInsert);
  }
  
  public class Node<E extends Comparable> implements Tree<E>{
  ...
    @Override
        public boolean search(E toFind){
        if(toFind.equals(value)){
       return true;
        }
        if(toFind.compareTo(value)<0){
            return left.search(toFind);
        }
        
        return right.search(toFind);
  }
    ...
  }
  
    public class Leaf<E extends Comparable> implements Tree<E>{
        ...
        @Override
            public boolean search(E toFind){
            return false;
            }
        ...
    }
  ```

* 트리의 끝까지 원소를 검색하게 되면, Leaf객체는  false를 반환한다. 해당 객체는 트리에 해당 값이 없다는 것을 나타낸다. 비슷한 방법으로 트리의 끝까지 원소를 검색할 경우, 값을 가진 새로운 Node객체로 Leaf객체를 변경시키는 insert 메서드를 만들 수 있다.
* 보통 트리의 균형을 맞출 때 드는 시간 복잡도는 검색, 삭제와 동일하게`O(log n)`이다.

* 이진 트리의 종류
  1. **AVL트리**

     어떤 노드든 모든 자식의 깊이 차이가 1이 넘지 않도록 만든다.

  2. **이진 힙(Heap) 트리**

     자식 노드가 부모 '보다 큰' 속성을 갖도록 균형이 잡힌 트리.

     여기서 '보다 큰' 속성을 갖도록 비교할 때에는 구현 방법에 차이가 있다.

     일반적인 순서를 비교할 때는 자바의 Comparable 인터페이스를 사용하고, 특정 순서를 비교할 땐 Comparator 객체를 이용한다.

     - **Heap**

       힙은 트리에서 가장 작은 원소를 최상위 루트에 두게한다. 따라서, 순서를 가진 큐나 리스트의 가장 작은 원소에 빠르게 접근해야만 하는 경우에 특히 더 유용하다. 

       그렇지만 보통 자료구조에서 남은 부분은 부분적으로만 정렬된다. 즉, 각 노드는 자신의 자식보다 작으므로, 원소들을 순서대로 위치고 싶을 때 가장 유용한 방법은 아니다.

  3. **n-진 트리**

     확률의 결과를 정의하거나, 선택하는 데 따라 두 개 이상의 결과가 있는 경우에 자주 사용한다.

## 3. Map

* 맵은 Hash라고 하며, 배열이나 사전과 관련 있는 키 - 값 형태의 저장소이다.

* 맵을 구현할 때에는 `Map` 인터페이스를 사용한다. 

* List 인터페이스와 다르게 Collection 인터페이스를 구현하지 않는다는 차이가 있다. 

* 키의 값은 트리 상에서 한 번만 나타난다.

* 다음 예제에서의 `overwriteKey`메서드 처럼 키를 다시 삽임하면 원본 키에 있던 값은 덮어씌워진다.

  ```java
  //Test
  public void overwriteKey(){
      final Map<String, String> preferences  = new HashMap<>();
      preferences.put("like", "jaccuzi");
      preferences.put("dislike", "steamRoom");
      
      assertEquals("jacuzzi",preferences.get("like"));
          
      preferences.put("like", 'sauna'); // key overWrite
      
      assertEquals("jacuzzi",preferences.get("like"));
  }
  ```

* `HashMap`클래스는 해시 테이블을 자바로 구현한 것으로, 클래스 구현에는 키-값 쌍을 나타내는`Entry`라는 내부 클래스가 있다. 원소들은 `Entry` 객체의 배열을 저장할 수도 있고, 배열 대신 `Entry`객체의 리스트로 저장할 수도 있다. 

* 이렇게 저장된 원소의 특정 키 값은 해당 키가 어디에 위치하고 있는지 정의한다. `Object`클래스에 정의된 `hashcode` 메서드는 `int` 타입 값을 반환하며, 이 값은 테이블의 어디에 키-값 쌍이 있는지 확인하는데 사용된다. 

* 이미 해시에 값이 저장된 인덱스에 또 값이 들어오고자 할때는 어떻게 해야할까? 이러한 상황을 해시 충돌(hash collision)이라고 한다.

* 해시 충돌을 해결하는 방법에는 두가지 방법이 있다.

  1. Open Addresing - 충돌이 일어나면 다른 인덱스에 저장하도록 한다.

     - 선형 탐사 :  해당 인덱스에 값이 이미 있다면 인덱스의 다음 값에 값이 있는 지 확인하고, 없다면 그 자리에 넣어준다.
     - 제곱 탐사 : 해당 인덱스에 값이 있다면, 해당인덱스 + 인덱스^2에 해당하는 곳에 값을 넣어준다.
     - 이중 해시 : 해시 함수를 2개 두고, 평소에는 해시 함수 1번만 쓰다가, 충돌이 발생하면 해시함수 2번에 저장하도록 하는 것

  2. Close Addressing - 충돌이 일어나도 해당 위치에 저장한다. 해당 방법들은 테이블을 간단하게 순회함으로써 키 복구를 수행하며 맞는 값을 찾을 때까지 각 원소들이 같은지 확인한다는 장점이 있다.

     * 버켓 : 배열의 한 요소가 배열인 경우이다. 즉 배열안에 또 배열을 넣는 것. 충돌이 일어나면 다른 위치로 재조정하지 않고, 해당 위치에 배열로 쌓는다.

       ![](https://t1.daumcdn.net/cfile/tistory/234EE34C53C9CCDC26)

     * 체이닝 : 충돌된 값들을 연결리스트로 연결한다.

       ![](https://t1.daumcdn.net/cfile/tistory/23307B4153C9CDC70A)

* `HashMap`이외에도 `TreeMap`클래스로 구현하는 방법도 있다. 

* `TreeMap`은 이진 트리 자료구조를 이용한다. 따라서 트리의 각 노드가 키-값 쌍이 된다. 

* `TreeMap`클래스는 키를 정렬 가능한 순서에 따라 저장하기 때문에 `hashcode` 메서드는 전혀 사용되지 않는다. 또한 균형을 맞춘 트리 구조로 구성되어 있기에, 검색, 삽입, 삭제 같은 모든 동작은 항상 O(log n)의 처리 성능을 발휘한다.

* `HashMap`과 `TreeMap`의 가장 큰 차이점은 트리맵에서는 컬렉션이 순서대로 저장되므로, 전체 컬렉션을 반복해서 순회할 때 키의 순서가 보장되지만, 해시맵은 그렇지 않다.

* 세번째로 `linkedHashMap`클래스가 있다. 이는 `Hashmap`과 동일하게 작동하기에 검색하는데 O(1)의 시간이 든다.

## 4. Set

* Set은 중복을 허용하지 않는 순서없는 객체들의 모음이다.

  ```java
  //Test 
  public void setExample(){
      final Set<String> set = new HashSet<>();
      set.add("Hello");
      set.add("welcome");
      set.add("goodbye");
      set.add("bye");
      set.add("Hello");
  }
  ```

* 여기서 "Hello"는 중복되기에, set객체에는 한개의 인스턴스만 저장된다.
