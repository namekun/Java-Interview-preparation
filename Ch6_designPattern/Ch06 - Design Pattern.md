# Ch06 - Design Pattern

※ 다양한 패턴이 있었으나, 시간상의 관계로 Singleton 패턴만 학습함

### 필요성 

`singleton pattern`이란 애플리케이션에서 오직 하나의 인스턴스만 생성한다는 것을 보장하는 패턴이다. 커넥션 풀, 디바이스 설정 객체 등의 경우, 인스턴스를 여러개 만들게 되면 자원을 낭비하게 되거나 버그를 발생시킬 수 있으므로, 오직 하나만 생성하고, 그 인스턴스를 사용하도록 하는 것이 이 패턴의 목적이다.

다수 서비스에서 보내는 연결 요청을 한 곳에서 쉽게 관리하고 설정할 수 있다는 장점이 있다.

### 구현

다음 코드는 문제가 있는 singleton클래스이다.

```java
public class Singleton{
    private static Singleton INSTANCE;
    
    public static Singleton getInstance(){
        if(INSTANCE == null){
            INSTANCE = new Singleton();
        }
        return INSTANCE;
    }
    
    public void singletonMethod(){
        //여기서 연산을 수행한다.
    }
}
```

다음 코드와 같은 방법을 `지연 초기화`라고 한다. 싱글턴 인스턴스는 제일 처음 필요로 하는 경우에 생성된다. 

자세히 보지 않으면 `getInstance()`메서드를 호출하든 같은 인스턴스를 반환할 것 처럼 보이지만, 인스턴스의 값이 만약 null이던, if문 때문에 인스턴스가 초기화되기 전에 스레드가 변경되면 두 번째, 혹은 더 많은 스레드가 `getInstance()` 메서드를 호출하게 되고, if 문은 true와 새로운 객체를 반환한다. 이런 경우 결과가 이상해지거나 오작동이 발생할 수 있고, 더 나쁜 경우에는 메모리 누수를 발생시켜 JVM이 실행되지 않을 수도 있다.

이런 경우에는 `Enum`타입을 사용하면 된다. 

Singleton 인스턴스를 하나의 원소로 가진 `Enum`타입으로 생성하면 JVM은 하나의 인스턴스만 받는 것을 보장한다.

```java
public enum SingletonEnum{
    INSTANCE;
    
    public void singletonMethod(){
        // 여기서 연산을 수행한다.
    }
}
```

그러나 싱글턴 패턴을 사용할 때 다음과 같은 문제점이 발생할 수 있음을 알아야한다.

즉, Singleton 인스턴스로 데이터베이서 저장 같은 무거운 연산을 실행할 경우, 작은 코드 부분 단위로 분리해서 테스트하기가 매우 어렵다.

클래스 의존성을 유지하려면, '싱글턴 같은' 객체를 이용하는 것이 관리를 단순화 할 수 있어서 더 좋다. 그리고 하나의 객체만 생성하려면, 의존성 주입 프레임워크를 사용하는 것이 더 좋다.
