# Ch8 - Java Basic

### 1. 원시 타입

Boolean, int, double과 같은 기본 타입은 원시 타입이라고 알려져 있다. JVM은 이들을 객체라고 알려진 참조 타입과는 다른 방식으로 다운다. 왜냐하면, 원시 타입들은 항상 값이 있는 상태, 즉 Null이 될 수 없기 때문이다.

|  타입   | 크기 |
| :-----: | :--: |
| boolean |  1   |
|  short  |  16  |
|   int   |  32  |
|  long   |  64  |
|  float  |  32  |
| double  |  64  |
|  char   |  16  |

* `char`은 유니코드 값이기에, 0부터 65,535까지이다.

원시 타입을 정의할 때, 값이 할당되지않았다면, 그 변수는 기본값으로 저장이된다.

boolean은 false, 다른 타입은 0으로 표현된다. int는 0, float는 0.0f로 표현된다.

컴파일러는 필요한 경우에 원시 타입을 적절한 타입으로 변환할 수 있다.

*원시 타입 변환*

```java
int value = Integer.Max_VALUE;
long biggerValue = value + 1;
```

`char`타입을 제외하면 컴파일러는 해당 값을 저장하기 위해 상위 타입을 자동으로 사용할 수 있다. 그러나 반대로 하위타입의 값은 사용이 불가하다. 하위 타입의 값을 사용하려면, 해당 값의 타입을 명시해서 변환해야한다.

*하위 타입으로 명시적으로 타입 변환하기*

```java
long veryLargeNumber = Long.MAX_VALUE;
int fromLargeNumber = (int) veryLargeNumber;
```

만약, `Integer.MAX_VALUE`와 `Integer.MIN_VALUE`보다 크거나 작은 값을 결과로 갖는 연산을 하려면 최하위 32비트만을 사용하고, 에러값을 반환한다. 

이런 경우를 `오버플로`라고 한다. 다음 코드는  `Integer.MAX_VALUE`의 절대값을 표현하는 데 int 타입을 사용할 수 없다는 걸 보여주는 유용한 예이다.

```java
//Test
public void absoluteOfMostNegativeValue(){
    final int mostNegative = Integer.MAX_VALUE;
    final int negated = Math.abs(mostNegative);
    assertFalse("No positive equivalent of Integer.MAX_VALUE", negated > 0);
}
```

### 2. 객체 이용하기

**객체**는 변수들의 컬렉션으로 정의할 수 있다. 간단하거나 복잡한 개체(entity)와 개체들에 관련된 연산을 제공하는 메서드들의 모음을 나타내기 때문.

원시 타입을 제외한 자바의 모든 다른 변수들은 참조 타입니다. 이들은 객체라고 더 잘 알려져 있다. 객체는 여러 가지 의미에서 원시 타입과 다르며, 가장 중요한 차이점은 빈 객체라는 것을 표현하는 Null이 존재한다. 즉 변수들은 Null로 설정될 수 있으며, 메서드 또한 `null`을 반환할 수 있다. 그러나 Null 참조에 대한 메서드를 호출할 수 없다. 호출하려고 하면 `NullPointerException`이 발생한다.

*`NullPointerException`이 발생하는 코드*

```java
public void expectNullPointerException(){
    final String s = null;
    final int StringLength = s.length();
}
```

#### '참조한다'라는건 어떤 의미를 갖는가?

원시타입에서의`int i = 42`로 변수가 선언되었을 때, 42라는 값이 메모리에 할당된다. 그 후, `int j = i`라는 형식으로 i에 의해 표현되는 값이 할당되었을 때, 메모리의 다른 위치에 같은 값을 할당한다. 일단 할당되고 나면 i는 j의 값에 영향을 미칠 수 없고, 그 반대도 마찬가지이다.

`List myList = new ArrayList(20)`은 생성된 객체를 변수에 할당할 때, `myList`는 할당된 메모리의 위치를 가리킨다. 표면적으로 원시 타입의 메모리할당과 객체의 메모리 할당은 같아보이지만, 그렇지 않다. 

그 이유는 몇 개의 변수가 동일한 속성으로 생성된 객체에 할당될 경우 같은 메모리 위치를 가리키기 때문이다. 즉, 한 인스턴스에 변경이 생기면 다른 인스턴스가 접근했을 때, 영향을 미치게 된다는 의미이다. 

*메모리에서 같은 인스턴스를 참조하는 변수들*

```java
//test
public void objectMemoryAssignment(){
    List<String> list1 = new ArrayList<>(20);
    
    list1.add("entry in List1");
    assertTrue(list1.size() == 1);
    
    List list2 = list1;
    list2.add("entry in list1");
    assertTrue(list1.size()==2);
}
```

#### `final`키워드는 객체 참조에 어떤 영향을 미치는가?

객체에 선언하는 final 키워드는 원시타입에 선언하는 final 키워드와 동일한 역할을 한다.

즉 , 변수 정의에서 지정된 값처럼 일단 할당이 되고 나면 메모리 위치가 변경되지 않는다.

앞에서 이야기 했던 것처럼 원시타입과 객체의 변수 정의와 메모리 할당은 전혀 다른 형태로 이루어지나, 객체 참조는 변경할 수 없어도 객체 내부의 값들은 개별 값들이 final이 아니라면 변경할 수 있다.

*final 키워드*

```java
public void finalReferenceChanges(){
    final int i = 42;
    //i <- 43; <- 해당 주석 부분을 해제하면 컴파일러에서 에러발생시킴
    
    final List<String> list = new ArrayList<>(20);
    // list = new ArrayList(50); <- 역시 여기도 해제하면 에러
    assertEquals(0, list.size());
    
    list.add("adding a new value into my list");
    assertEquals(1, list.size());
    
    list.clear();
    assertEquals(0, list.size());
}
```

#### 객체의 가시성 수정자는 어떻게 작동하는가?

**가시성 수정자**는 클래스의 캡슐화된 상태와 인스턴스 행동을 조정하는 메서드의 접근을 제어하는 역할을 한다.

| 가시성 | 수정자    | 범위                                                         |
| ------ | --------- | ------------------------------------------------------------ |
| Least  | private   | **같은 클래스의 모든 인스턴스에서 접근 가능**. 하위 클래스에서는 접근 불가능. |
|        | \<none>   | 같은 패키지의 모든 클래스에서 접근 가능                      |
|        | protected | 모든 하위 클래스에서 접근 가능                               |
| Most   | public    | 어디서든 접근 가능                                           |

*private 멤버 변수에 접근하기*

```java
public class complex{
    private final double real;
    private final double imaginary;
    
    public Complex(final double r, final double i){
        this.real = r;
        this.imaginary = i;
    }
}

public Complex add(final Complex other){
    return new Complex(this.real + other.real, this.imaginary + other.imaginary);

// 간단하게 구현하기 위해 hashcode는 생략

@Override
public boolean equals(Object o){
    if(this == o){
        return true;
    }
    
    if(o == null || getClass() != o.getClasS()){
        return true;
    }
    
    Complex complex = (Complex) o;
    if(Double.compare(complex.imaginart, imaginary) != 0)
        return false;
    
    if(Double.compare(complex.real, real) != 0)
        return false;
    
    return false;
	}
}

//Test
public void complexNumberAddition(){
    final Complex expected = new Complex(6, 2);
    
    final Complex a = new Complex(8, 0);
    final Complex b = new Complex(-2, 2);
    
    assertEquals(a.add(b), expected);
}
```

#### 메서드와 변수에 사용되는 static 키워드의 역할은 무엇인가?

정적(static) 메서드와 정적 변수는 클래스 내부에 정의하지만, 인스턴스에는 속하지 않는다. 이는 모든 인스턴스에 공통으로 적용되는 사항이므로, 대개 특정 인스턴스보다는 클래스 이름을 통해 정적 메서드와 정적 변수에 접근한다.

*클래스 멤버 변수에 접근하기*

```java
public class ExampleClasS{
    public static int EXAMPLE_VALUE = 6;
}

//test
public void staticVariableAccess(){
    assertEquals(ExampleClass.EXAMPLE_VALUE, 6);
    
    ExampleClass c1 = new ExampleClass();
    ExampleClass c2 = new ExampleClass();
    c1.EXAMPLE_VALUE = 22; // 가능하지만 추천하진않는다.
    assertEquals(ExampleClass.EXAMPLE_VALUE, 22);
    assertEquals(c2.EXAMPLE_VALUE, 22);
}
```

#### 다형성과 상속이란 무엇인가?

**다형성**은 행동의 특정 타입에 대한 정의를 만들 수 있게 하고, 행동을 구현하는 수많은 다른 클래스를 갖게 한다.

**상속**은 부모 클래스에서 클래스의 행동과 정의를 가져다 사용해줄 수 있게 해준다. 새로운 클래스를 정의할 때, 이전에 정의된 클래스에서 정의와 상태를 상속할 수 있고, 새로운 행동을 추가하거나, 새로운 타입에 대한 행동을 `Override`할 수 있다.

*상속을 통해 정사각형 만들기*

```java
public class Rectangle{
    private final int width;
    private final int height;
    
    public Rectangel(final int width, final int height){
        this.width = width;
        this.height = height;
    }
    
    public int area(){
        return width * height;
    }
}

public class Square extends Rectangle{
    
    public Square(final int sideLength){
        super(sideLength, sideLength);
    }
}
```

* `super`
  * 자신을 가리키는 키워드가 `this`면 부모를 가리키는 키워드는`super`이다.
  * `super()`는 부모의 생성자를 의미한다.
  * 부모의 생성자를 임의로 호출하지 않으면, 부모의 class의 기본 생성자가 자동으로 호출된다.

*자바 컬렉션에서 다형성 사용하기*

```java
public void polymorphicList(){
    List<Rectangle> rectangles = new ArrayList<>(3);
    rectangles.add(new Rectangle(5, 1));
    rectangles.add(new Rectangle(2, 10));
    rectangles.add(new Square(9));
}
```

실제로 `ArrayList`클래스와 관계된 것은 `Rectangle`클래스와만 연계해서 처리한다. 따러서 `Rectangle`과 `Square`클래스의 차이를 이해하지 못 해도 된다. `Square`is-a `Rectangle`관계를 살펴보면, 코드는 잘 작동할 것이기 때문이다.

또한, `Square`클래스에 구체적으로 정의되어 있는 메서드는`Rectangle`클래스에서는 사용할 수 없다는 점도 기억해두자. 따라서 `rectangle`리스트의 모든 사용자는 `Square`클래스의 메서드를 사용할 수 없다.

#### 객체의 일부 메서드가 오버라이드 되었을 때 어떻게 사용되는가

JVM 상에서 실행되는 모든 클래스는 `java.lang.Object`를 상속하므로, `final`메서드를 제외한`public`이나, `protected`메서드는 오버라이드 될 수 있다.

`equals`메서드는 두 객체의 참조가 논리적으로 같은지 확인하는 데 사용한다. 일반적으로 객체의 동일성을 확인하는 데는 많이 사용하지 않고, 메서드 오버라이딩의 동일성을 확인할 필요가 있을 때만 사용해야한다.

`hashcode`메서드에는 두 개의 같은 객체가 반드시 같은 값을 반환해야한다는 규칙이 있다. 그 반대의 경우에는 true가 아니다.(단, 두 개의 객체가 같은 hashcode메서드의 값을 반환하더라도 반드시 같은 객체인 것은 아니다.)

아래의 코드에서 `Set`인터페이스는 예상대로 작동하지 않는데, 이는 `person`객체에 `equals` 메서드를 구현하지 않아, 어떻게 비교해야할지 모르기 때문이다. 

*제대로 구현하지 않은 Set 인터페이스의 오작동*

```java
public class Person{
    private final String name;
    private final int age;
    
    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }
    
    @Override
    public int hashcode(){
        return name.hashcode() * age;
    }
}

//Test
public void wrongSetBehavior(){
    final Set<Person> people = new HashSet<>();
    
    final Person person1 = new Person("Alice", 28);
    final Person person2 = new Person("Bob", 30);
    final Person person3 = new Person("Charlie", 22);
    
    final boolean person1Added = people.add(person1);
	final boolean person2Added = people.add(person2);
    final boolean person3Added = people.add(person3);
    
    assertTrue(person1Added && person2Added && person3Added);
    
    //person1 객체와 논리적으로 같다.
    final Person person1Again = new Person("Alice", 28);
    
    // Alice라는 매개변수는 이미 설정되어 있으므로, false를 반환해야한다.
    final boolean person1AgainAdded = people.add(person1Again);
    
    // equals 메서드가 구현되어 있지 않으므로, true를 반환한다.
    assertTrue(person1AgainAdded); assertEquals(4, people.size());
}
```

그 밖에 `hashcode`와 `equals` 메서드의 관계에서 지켜야하는 규칙은 `hashcode`나 `equals` 메서드를 오버라이딩할 때, 반드시 둘 다 함께 오버라이드 해야한다. 라는 것이다.

### 3. 자바배열

자바 배열에서 기억해야할 가장 중요한 사항은 배열을 객체 취급한다는 것이다. 배열이 객체라는 의미는 참조로 전달될 수 있다는 뜻이다. 해당 배열을 참조하는 모든 것은 특정 상황에 의해 변경될 수 있다.

*java에서 배열이 어떻게 참조되는가?*

```java
public void arrayReference(){
    final int [] myArray = new int[]{0, 1, 2, 3, 4, 5};
    int [] arrayReference2 = myArray
        
    arrayReference2[5] = 99;
}

...
    	public static void main(String[] args) {
		arrayReference(); // 0 1 2 3 4 99로 출력된다.
	}
```

### 4. String

String 클래스는 자바 라이브러리에 간단히 정의된 클래스 이지만, 자바에서 매우 중요하고, 광범위 하게 사용된다. JVM과 컴파일러는 특정 상황에서 특별한 방법으로 String 객체를 처리한다.

String 객체 대부분은 원시타입 취급한다. 그러므로 String 리터럴을 생성할 때, new 키워드를 이용할 필요가 없다.

String클래스에서는 String객체의 값을 변경하는 것처럼 보이는 모든 메서드가 실제로는 String '**인스턴스**'를 반환한다. 그렇기에, String의 값은 절대 변하지 않으며 바꿀 수 없다는 String 객체의 성질을 우리는 기억해야할 필요가 있다.

**인스턴스로 표현되는 값은 절대 변경할 수 없다는 것을 기억해두자.** 이러한 불변성은 많은 장점이 있다. 그중 하나는 스레드에 안전하다는 점이다. 불변 객체는 많은 동시 스레드에서 사용할 수 있고, 각 스레드는 그 값들을 절대 변하지 않을 것이라는 걸 확신할 수 있기 때문에, 잠금이나 복잡한 스레드 조절이 필요없다.

이와 같은 성질을 갖는 불변 클래스는 String 말고도, Integer, Double, Character, BigInteger등과 같은 숫자형 클래스가 있다.

### 5. Generic

제네릭은 '매개변수화된 타입'이라고 할 수 있다. 컬렉션 클래스에서 제네릭을 사용했을 때, 컴파일러는 특정 타입만 포함될 수 있게 컬렉션을 제한한다. 다음 코드를 보자

*제네릭 적용전*

```java
public class Generic {
	private List authors;
	
	private class Author{
		private final String name;
		private Author(final String name) {
			this.name = name;
		}
		public String getName() {
			return name;
		}
		
		//Before
		public void createAuthors() {
			authors = new ArrayList();
		
			authors.add(new Author("king"));
			authors.add(new Author("hyuk"));
			authors.add(new Author("been"));
		}
		
		//Test
		public void authorListAccess() {
			final Author author = (Author) authors.get(2);
			assertEquals("king", author.getName());
		}
	}

}

```

위의 코드에서 List 인스턴스에 String 객체를 추가하고, 같은 인스턴스에서 String객체들에 접근한다. getName 메서드를 호출했을 때, 객체는 String타입으로 형변환이 되어야하는데, List 인스턴스는 다형성이 있으므로 getName 메서드는 Object객체를 반환한다. 이때, List인스턴스에는 Author 클래스의 인스턴스만 접근할 수 있도록 제한을 두고 있지않으므로 개발자가 다음과 같으 List 인스턴스를 잘못 사용할 수도 있다.

```java
public void useStrings(){
    authores.add("hihi");
    
    final String authorAsString = (String) authors.get(authores.size()-1);
    assertEquals("hihi", authorAsString);
}
```

위의 코드에서는 몇가지 이슈를 발견할 수 있다. 리스트에서 사용되는 인스턴스의 타입에는 제한이 없다. 하지만,  "리스트에 이 타입들이 확실히 들어갈 수 있는가?"에는 의문이 남는다. 확실하지 않다면, 실행 중에 `ClassCastException`이 발생할 것이다. 하지만 직접 타입변환을 하는 것은 번거롭다.

**그렇기에 우리는 제네릭을 사용하는 것이다.** 제네릭을 사용하면 실행할 때 발생할 수 있는 모든 잠재적인 예외가 실제 컴파일러 에러로 이어진다. 이러한 예외는 개발 수명 주기에서 훨씬 빨리 발견되어 코드를 신속하게 수정하고 정리할 수 있도록 해준다.

*제네릭을 사용한 코드*

```java
public class Generic {
	private List<Author> authors; // 제네릭 사용
	
	private class Author{
		private final String name;
		private Author(final String name) {
			this.name = name;
		}
		public String getName() {
			return name;
		}
		
		//Before
		public void createAuthors() {
			authors = new ArrayList();
		
			authors.add(new Author("king"));
			authors.add(new Author("hyuk"));
			authors.add(new Author("been"));
		}
		
		//Test
		public void authorListAccess() {
			final Author author = authors.get(2);
			assertEquals("king", author.getName());
		}
	}

}
```

`//test`부분을 보면 형변환을 사용하지않기에 좀더 코드 읽기가 편해진 것을 알 수 있다.

`Authors`인스턴스는 `Author`타입의 객체만 전달받을 수 있도록 제한되어 있다. 즉 다른 테스트에서 `useString`메서드는 더 이상 컴파일 되지 않으며, 컴파일러는 해당 문제를 알게 된다. 

컬렉션 API에 잇는 모든 클래스는 제네릭을 사용해서 만들었다. 이때 List 인터페이스와 구현은 한 가지 타입의 매개변수만 받는다. 이는 `Set`인터페이스에서도 마찬가지이다. 

제네릭 타입은 중첩해서 사용이 가능하다. 예를 들어 `HashMap<Integer, List<String>>`구문에서 HashMap은 Integer을 키로 갖고, String 타입의 리스트에 매핑되는 Map 인터페이스에 사용할 수 있다.

***E의 의미***

```java
public class GenericStack<E>{
...
```

위의 코드에서 E는 매개변수화된 타입변수를 표시하며, 어느 위치에서든 이 기호를 사용할 수 있다. 위 코드는 '원소'를 위해 컬렉션 API에서 사용하는 모든 타입 변수를 반영한다는 의미이다. 

### @Override 어노테이션

`@Override`어노테이션은 컴파일러에게 슈퍼 클래스의 메서드가 오버라이드 되었다는 것을 알려주므로 컴파일 할 때 유용하다. 단, 슈퍼클래스에 일치하는 메서드 시그니처가 없으면 에러가 발생하고 컴파일되지않는다.

일반적으로 메서드를 오버라이딩할때, 이 어노테이션을 표기함으로써 실수하지 않게 하는 용도로 많이 사용된다.

```java
public class Vehicle{
    public String getName(){
        return "Generic Vehicle";
    }
}

public class Car extends Vehicle{
    public String getname(){
        return "Car";
    }
}

public static void main[String[]args]{
    Car c= new Car();
    System.out.println(c.getName());
}
```

위 코드를 실행했을 때, 출력되는 값은 `Generic Vehicle`인데, 이는 Car 클래스에 있는 메서드의 이름이 getName이 아니라 getname으로 했기 때문이다. 자바의 모든 식별자는 대소문자를 구분하므로 이는 오타로 인해 발생한 에러이다. 

이러한 에러를 방지하기 위해서 `@Override`를 `getName()`메서드 선언 위에 표시하면 컴파일러가 위의 코드와 같은 상황에 에러가 발생했음을 알려준다.

### 명명규칙 이해하기

1. Class

   * 클래스는 항상 대문자로 시작하고, 카멜케이스를 따른다.

2. 변수와 메서드

   * 변수와 메서드는 항상 소문자로 시작하고 카멜케이스를 따른다.

3. 상수

   * Static final 인스턴스 변수는 변경할 수 없는 상수이다. 
   * 상수의 명명규칙은 모두 대문자를 사용하며, 단어 사이는 밑줄로 연결한다.

   *예시*

   ```java
   public static final int HTTP_OK = 200;
   public static final String EXPECTED_TEST_RESPONCE = "YES";
   ```


### 예외처리하기

다음은 각 타입의 예제와 함께 예외 처리 구조를 보여주는 예이다.

![](https://t1.daumcdn.net/cfile/tistory/21476F3E577E91080E)

모든 클래스는 `Throwable` 클래스를 확장해서 예외 처리를 할 수 있다. `Throwable`은 `Error`와 `Exception`이라는 두 개의 하위 클래스를 갖는데, 필요한 곳에서 `Exception` 클래스를 확인하고 수정하는 것은 대개 프로그래머의 책임이라고 할 수 있다. `Error`은 `OutOfMemory`와 `NoClassDefFoundError` 클래스 처럼 프로그래머 스스로 복구할 수 있는 것은 아니다. 

예외는  '런타임 예외'이거나 '확인해야하는 예외' 두 가지로 구분된다. 런타임 예외는 모두 `RuntimeException`의 하위 클래스이고, 확인해야하는 예외는 모두 다른 예외이다.

확인해야하는 예외를 처리하는 메서드(또는 생성자)는 사용할 때 메서드 정의에 명시적으로 예외가 정의되어야 한다. 코드를 호출하는 모든 호출자들은 해당 예외를 처리할 수 있어야 한다. 즉, 메서드 호출자에게 전달하거나 `try/catch/finally`문으로 예외를 적절히 처리해줘야 한다.

### 자바 컬렉션 프레임워크

자바 컬렉션 프레임워크는 `java.util`패키지에 포함되며, 모든 단일 원소 컬렉션은 `Collection` 인터페이스가 구현되어 있다. 컬렉션에는 얼마나 많은 원소가 있는지 확인하는 `size()`와 같은 다양한 메서드가 있다. 

`Map` 인터페이스는 `Collection`인터페이스를 구현하지 않는다. 보통 자바는 매핑과 컬렉션을 서로 구분하기 때문.

*컬렉션 프레임 워크 구조*

![](https://t1.daumcdn.net/cfile/tistory/2630264F548D957F09)

