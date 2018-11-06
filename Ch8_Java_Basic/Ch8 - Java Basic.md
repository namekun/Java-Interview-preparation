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

#### 객체의 일부 메서드가 오버라이드 되었을 때 어떻게 사용되는지 설명해라.

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

자바 배열에서 기억해야할 가장 중요한 사항은 배열을 객체 취급한다는 것이다.