//
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.ParameterizedType;
//import java.util.ArrayList;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public abstract class Controller<T> {
//
//    public abstract T setV(T obj);
//
//    private final T t = (T) this;
//
//    private final Class<T> persistentClass;
//
//    public Controller() {
//        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
//                .getGenericSuperclass()).getActualTypeArguments()[0];
//    }
//
//    public T get() {
//        return (T) persistentClass;
//    }
//
//    private <T extends Controller<T>> T getNewInstance() {
//        try {
//            return (T) persistentClass.getDeclaredConstructor().newInstance();
//        } catch (IllegalAccessException
//                | IllegalArgumentException
//                | InstantiationException
//                | NoSuchMethodException
//                | SecurityException
//                | InvocationTargetException ex) {
//            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
//
//    public <T extends Controller<T>> ArrayList<T> abc() {
//        ArrayList<T> arrayList = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            T obj = (T) getNewInstance();
//            arrayList.add(obj.setV(obj));
//        }
//        return arrayList;
//    }
//
////    private static final Branch instance = new Branch();
////    
////    public static Branch getInstance() {
////        return instance;
////    }
////    private final UserEntity userEntity = new UserEntity();
////    User user = User.getInstance();
////
////    private static class SomeContainer<E> {
////
////        E createContents(Class<E> clazz) {
////            try {
////                return clazz.newInstance();
////            } catch (InstantiationException | IllegalAccessException ex) {
////                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
////            }
////            return null;
////        }
////    }
////public class JustDoIt extends Application {
////
////    private final TableView<Person> table = new TableView<>();
////    private final ObservableList<Person> data
////            = FXCollections.observableArrayList(
////                    new Person("Jacob", "Smith"),
////                    new Person("Isabella", "Johnson"),
////                    new Person("Ethan", "Williams"),
////                    new Person("Emma", "Jones"),
////                    new Person("Michael", "Brown")
////            );
////
////    public static void main(String[] args) {
////        launch(args);
////    }
////
////    @Override
////    public void start(Stage stage) {
////        stage.setWidth(450);
////        stage.setHeight(500);
////
////        TableColumn firstNameCol = new TableColumn("First Name");
////        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
////
////        TableColumn lastNameCol = new TableColumn("Last Name");
////        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
////
////        TableColumn actionCol = new TableColumn("Action");
////        actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
////
////        Callback<TableColumn<Person, String>, TableCell<Person, String>> cellFactory
////                = //
////                new Callback<TableColumn<Person, String>, TableCell<Person, String>>() {
////            @Override
////            public TableCell call(final TableColumn<Person, String> param) {
////                final TableCell<Person, String> cell = new TableCell<Person, String>() {
////
////                    final Button btn = new Button("Just Do It");
////
////                    @Override
////                    public void updateItem(String item, boolean empty) {
////                        super.updateItem(item, empty);
////                        if (empty) {
////                            setGraphic(null);
////                            setText(null);
////                        } else {
////                            btn.setOnAction(event -> {
////                                Person person = getTableView().getItems().get(getIndex());
////                                System.out.println(person.getFirstName()
////                                        + "   " + person.getLastName());
////                            });
////                            setGraphic(btn);
////                            setText(null);
////                        }
////                    }
////                };
////                return cell;
////            }
////        };
////
////        actionCol.setCellFactory(cellFactory);
////
////        table.setItems(data);
////        table.getColumns().addAll(firstNameCol, lastNameCol, actionCol);
////
////        Scene scene = new Scene(new Group());
////
////        ((Group) scene.getRoot()).getChildren().addAll(table);
////
////        stage.setScene(scene);
////        stage.show();
////    }
////
////    public static class Person {
////
////        private final SimpleStringProperty firstName;
////        private final SimpleStringProperty lastName;
////
////        private Person(String fName, String lName) {
////            this.firstName = new SimpleStringProperty(fName);
////            this.lastName = new SimpleStringProperty(lName);
////        }
////
////        public String getFirstName() {
////            return firstName.get();
////        }
////
////        public void setFirstName(String fName) {
////            firstName.set(fName);
////        }
////
////        public String getLastName() {
////            return lastName.get();
////        }
////
////        public void setLastName(String fName) {
////            lastName.set(fName);
////        }
////
////    }
////}
//}
