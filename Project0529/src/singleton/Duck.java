package singleton;
/*
 아래의 오리클래스의 인스턴스를 오직 1번만 생성하도록 개발자가 
 제한할수 있을까??? 
 
 현재 오리 클래스와 같이 클래스의 인스턴스를 오직 하나만 생성할수 있도록
 제한을 가하는 클래스 정의 기법을 가리켜 SingleTon Pattern이라 한다!
 이 명칭은 누가 정했나?
 90년대 중반에 Gang Of Four = GOF라 불리는 4명의 개발자가 공동저자
 로 책을 출판함... 이 책이 IT 분야에서 큰 획을 그음..!
 전세계 개발자들이 사용하는 개발 패턴에 이름을 붙인 후로, 개발자들간 용어가
 통일되었다는 의미가 있다..20여가지의 패턴이 소개됨... 
 */
public class Duck {
	static Duck instance;
	
	//생성자를 private으로 지정!
	private Duck() {
		
	}
	//외부의 클래스가 이 클래스의 인스턴스를
	//가져갈수있도록 메서드를 제공해주자
	public static Duck getInstance() {
		//태어난 적이 없다면 아래의 new 
		if(instance == null) {
			instance = new Duck();
		}
		return instance;
	}
}
