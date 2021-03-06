package season_two.Atomic_Reference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

@Getter
@Setter
@AllArgsConstructor
@ToString
class User {
    private String name;
    private int age;
}

public class AtomicReferenceDemo {
    public static void main(String[] args) {

        User zs = new User("zs", 22);
        User ls = new User("ls", 25);


        AtomicReference<User> userAtomicReference = new AtomicReference<>();
        userAtomicReference.set(zs);
        userAtomicReference.set(new User("zs", 22));

        System.out.println(userAtomicReference.compareAndSet(zs, ls) + "\t" + userAtomicReference.get().toString());
        System.out.println(userAtomicReference.compareAndSet(zs, ls) + "\t" + userAtomicReference.get().toString());
    }


}

