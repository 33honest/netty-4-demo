namespace java thrift.generated

typedef i16 short
typedef i32 int
typedef i64 long
typedef bool boolean
typedef string String

struct People {
    1: optional String name,
    2: optional int age,
    3: optional boolean married
}

exception DataException {
    1: optional String message,
    2: optional String callStack,
    3: optional String date
}

service personService {

    People getPersonByUsername(1: required String username) throws (1: DataException dataException),
    void savePerson(1: required People people) throws (1: DataException dataException)
}