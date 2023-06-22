import data.*;
import exeptions.*;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Asks a user a group's value.
 */

public class ProductAsk {


    private final long MAX_X = 504;
    private final double MIN_Y = -692;

    private Scanner userScanner;

    private boolean fileMode;
    public ProductAsk(Scanner userScanner) {
        this.userScanner = userScanner;
        fileMode = false;
    }

    /**
     * Sets a scanner to scan user input.
     * @param userScanner Scanner to set.
     */

    public void setUserScanner(Scanner userScanner) {this.userScanner = userScanner; }

    /**
     * @return Scanner, which uses for user input.
     */

    public Scanner getUserScanner() {return userScanner;}

    /**
     * Sets group asker mode to 'File Mode'.
     */

    public void setFileMode() {fileMode = true;}

    /**
     * Sets group asker mode to 'User Mode'.
     */

    public void setUserMode() {fileMode = false;}

    /**
     * Asks a user the group's name.
     * @return Group's name.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */

    public String askName() throws IncorrectInputInScriptException {
        String name;
        while (true) {
            try {
                System.out.println("Введите имя:");

                name = userScanner.nextLine().trim();
                if (fileMode) System.out.println(name);
                if(name.equals("")) throw new MustBeNotEmptyException();
                break;
            } catch (NoSuchElementException exception) {
                System.out.println("Имя не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (MustBeNotEmptyException exception) {
                System.out.println("Имя не может быть пустым!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                System.out.println("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return name;
    }

    /**
     * Asks a user the group's X coordinate.
     * @return Group's X coordinate.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */

    public Long askX() throws IncorrectInputInScriptException {
        String strX;
        Long x;
        while (true) {
            try {
                System.out.println("Введите координату X:");

                strX = userScanner.nextLine().trim();
                if (fileMode) System.out.println(strX);
                x = Long.parseLong(strX);
                if (x > MAX_X) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                System.out.println("Координата X не распознана!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                System.out.println("Координата x не может превышать " + MAX_X + "!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                System.out.println("Координата X должна быть представлена числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                System.out.println("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return x;

    }

    /**
     * Asks a user the group's Y coordinate.
     * @return Group's Y coordinate.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */

    public double askY() throws IncorrectInputInScriptException {
        String strY;
        double y;
        while (true) {
            try {
                System.out.println("Введите координату Y:");

                strY = userScanner.nextLine().trim();
                y = Double.parseDouble(strY);
                if (y < MIN_Y) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                System.out.println("Координата Y не распознана!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                System.out.println("Координата Y не может быть меньше " + MIN_Y + "!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                System.out.println("Координата Y должна быть представлена числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                System.out.println("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return y;
    }

    /**
     * Asks a user the group's coordinates.
     * @return group's coordinates.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */

    public Coordinates askCoordinate() throws IncorrectInputInScriptException {
        Long x;
        double y;
        x = askX();
        y = askY();
        return new Coordinates(x, y);
    }


    /**
     * Asks a user the number of students in this group.
     * @return Group's studentsCount.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */

    public double askPrice() throws IncorrectInputInScriptException {
        String strPricet;
        double price;
        while (true) {
            try {
                System.out.println("Введите Price продукта");

                strPricet = userScanner.nextLine().trim();
                if (fileMode) System.out.println(strPricet);
                price = Double.parseDouble(strPricet);
                if (price <= 0) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                System.out.println("цена не распознана!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                System.out.println("цена должна быть больше нуля!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                System.out.println("цена должна быть представлено числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                System.out.println("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return price;
    }




    /**
     * Asks a user Unit Of Measure.
     * @return Unit of Measure.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */

    public OrganizationType askOrganozationType() throws IncorrectInputInScriptException {
        String strOrganizationType;
        OrganizationType organizationType;
        while (true) {
            try {
                System.out.println("Введите тип организации");

                strOrganizationType = userScanner.nextLine().trim();
                if (fileMode) System.out.println(strOrganizationType);
                organizationType = OrganizationType.valueOf(strOrganizationType.toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                System.out.println("тип организации не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalArgumentException exception) {
                System.out.println("тип организации нет в списке!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                System.out.println("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return organizationType;
    }
    /**
     * Asks a user Unit Of Measure.
     * @return Unit of Measure.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */

    public UnitOfMeasure askUnitOfMeasure() throws IncorrectInputInScriptException {
        String strFormOfEducation;
        UnitOfMeasure unitOfMeasure;
        while (true) {
            try {
                System.out.println("Введите меру измерения");

                strFormOfEducation = userScanner.nextLine().trim();
                if (fileMode) System.out.println(strFormOfEducation);
                unitOfMeasure = UnitOfMeasure.valueOf(strFormOfEducation.toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                System.out.println("мера измерения не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalArgumentException exception) {
                System.out.println("мера измерения нет в списке!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                System.out.println("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return unitOfMeasure;
    }
    /**
     * Asks a organization name.
     * @return organization name.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */

    public String askOrganizationName() throws IncorrectInputInScriptException {
        String groupAdminName;
        while (true) {
            try {
                System.out.println("Введите имя организации:");

                groupAdminName = userScanner.nextLine().trim();
                if  (fileMode) System.out.println(groupAdminName);
                if (groupAdminName.equals("")) throw new MustBeNotEmptyException();
                break;
            } catch (NoSuchElementException exception) {
                System.out.println("Имя организации не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (MustBeNotEmptyException exception) {
                System.out.println("Имя организации не может быть пустым!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                System.out.println("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return groupAdminName;
    }
    /**
     * Asks a organization shortname.
     * @return organization shortname.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */

    public String askOrganizationShortname() throws IncorrectInputInScriptException {
        String groupAdminName;
        while (true) {
            try {
                System.out.println("Введите сокращение организации:");

                groupAdminName = userScanner.nextLine().trim();
                if  (fileMode) System.out.println(groupAdminName);
                if (groupAdminName.equals("")) throw new MustBeNotEmptyException();
                break;
            } catch (NoSuchElementException exception) {
                System.out.println("сокращение организации не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (MustBeNotEmptyException exception) {
                System.out.println("сокращение организации не может быть пустым!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                System.out.println("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return groupAdminName;
    }

    public Address askAdress() throws IncorrectInputInScriptException {
        Address addressOrg = new Address();
        String adress;
        while (true) {
            try {
                System.out.println("Введите адресс организации:");

                adress = userScanner.nextLine().trim();
                addressOrg.setStreet(adress);
                if  (fileMode) System.out.println(adress);
                if (adress.equals("")) throw new MustBeNotEmptyException();
                break;
            } catch (NoSuchElementException exception) {
                System.out.println("адресс организации не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (MustBeNotEmptyException exception) {
                System.out.println("адресс организации не может быть пустым!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                System.out.println("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return addressOrg;
    }

    /**
     * Asks a user the group admin.
     * @return group admin.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */

    public Organization askOrganization() throws IncorrectInputInScriptException {
        String name = askOrganizationName();
        String shortName = askOrganizationShortname();
        OrganizationType organizationType = askOrganozationType();
        Address address = askAdress();


        return new Organization(12L,name,shortName,organizationType,address);

    }


    public Product askProduct() throws IncorrectInputInScriptException{
        int id;
        String name = askName();
        Coordinates coordinates = askCoordinate();
        UnitOfMeasure unit = askUnitOfMeasure();
        double price = askPrice();
        Organization organization = askOrganization();
        return new Product(name,coordinates,price,unit,organization);
    }
    /**
     * Asks a user a question.
     * @return Answer (true/false).
     * @param question A question.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public boolean askQuestion(String question) throws IncorrectInputInScriptException {
        String finalQuestion = question + " (+/-):";
        String answer;
        while (true) {
            try {
                System.out.println(finalQuestion);
                answer = userScanner.nextLine().trim();
                if (fileMode) System.out.println(answer);
                if (!answer.equals("+") && !answer.equals("-")) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                System.err.println("Ответ не распознан!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {

                System.err.println("Ответ должен быть представлен знаками '+' или '-'!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                System.err.println("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return (answer.equals("+")) ? true : false;
    }

    @Override
    public String toString() {return "GroupAsker (вспомогательный класс для запросов пользователю)";}

}
