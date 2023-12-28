package com.example.reviewcompanion;

public class DatabaseVariableHolder {
    String subject, question, choice_1, choice_2, choice_3, choice_4, answer;
    String category_score, score, date;

    public final String[] Category = {
            "AFAR",
            "Auditing",
            "FAR",
            "MS",
            "Law",
            "Tax"
    };

    public final String[][] questions = {
            VariableAFAR.Questions(),
            VariableAuditing.Questions(),
            VariableFAR.Questions(),
            VariableMS.Questions(),
            VariableLaw.Questions(),
            VariableTax.Questions(),
    };

    public final String[][] ChoiceA = {
            VariableAFAR.ChoiceA(),
            VariableAuditing.ChoiceA(),
            VariableFAR.ChoiceA(),
            VariableMS.ChoiceA(),
            VariableLaw.ChoiceA(),
            VariableTax.ChoiceA()
    };
    public final String[][] ChoiceB = {
            VariableAFAR.ChoiceB(),
            VariableAuditing.ChoiceB(),
            VariableFAR.ChoiceB(),
            VariableMS.ChoiceB(),
            VariableLaw.ChoiceB(),
            VariableTax.ChoiceB()
    };
    public final String[][] ChoiceC = {
            VariableAFAR.ChoiceC(),
            VariableAuditing.ChoiceC(),
            VariableFAR.ChoiceC(),
            VariableMS.ChoiceC(),
            VariableLaw.ChoiceC(),
            VariableTax.ChoiceC()
    };
    public final String[][] ChoiceD = {
            VariableAFAR.ChoiceD(),
            VariableAuditing.ChoiceD(),
            VariableFAR.ChoiceD(),
            VariableMS.ChoiceD(),
            VariableLaw.ChoiceD(),
            VariableTax.ChoiceD()
    };
    public final String[][] CorrectAns = {
            VariableAFAR.Answers(),
            VariableAuditing.Answers(),
            VariableFAR.Answers(),
            VariableMS.Answers(),
            VariableLaw.Answers(),
            VariableTax.Answers()
    };
}
