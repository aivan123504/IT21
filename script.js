class Person {
    #name;
    #age;
    #occupation;
    #major;
    constructor(name, age, occupation, major) {
        this.#name = name;
        this.#age = age;
        this.#occupation = occupation;
        this.#major = major;
    }
    get name() {
        return this.#name;
    }

    get age() {
        return this.#age;
    }

    get occupation() {
        return this.#occupation;
    }

    get major() {
        return this.#major;
    }
}


class Student extends Person {

    constructor(name, age, occupation, major) {
        super(name, age, occupation, major);
    }
}

const student = new Student("Aivan Dave", 20, "Student", "Programming");

console.log("Name: " + student.name);
console.log("Age: " + student.age);
console.log("Occupation: " + student.occupation);
console.log("Major: " + student.major);

document.getElementById("information").innerHTML = "Name: " + student.name + "<br>" +
    "Age: " + student.age + "<br>" +
    "Occupation: " + student.occupation + "<br>" +
    "Major: " + student.major