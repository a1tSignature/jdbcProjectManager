package di.com.a1tSign.projectManager.position.developer;

public interface DeveloperAction {

    default void refactoring() {
        System.out.println("Do refactoring...");// Вынесу рефакторинг в дефолт, почистит везде реализации
    }
}
