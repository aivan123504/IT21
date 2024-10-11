class Person{
    constructor(name){
         this.name = name;
     }

     sayName(){
         console.log(this.name);
       }
}

class Student extends Person{
   constructor(name, rollNumber){
      super(name);
      this.rollNumber = rollNumber;
  }

      logDetails(){
      console.log(`Name: ${this.name}, Roll
      number: ${this.rollNumber}`)
      }

      sayName(){
      console.log("From Student");
      super.sayName();
      }

}
      var student = new Student("Aivan", 1);
      student.logDetails();
      student.sayName();