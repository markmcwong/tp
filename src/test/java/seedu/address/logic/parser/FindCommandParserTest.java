package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PRICE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TRIPDAY_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TRIPTIME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_IT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRIPDAY_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.TripDayContainsKeywordsPredicate;
import seedu.address.model.TripTimeContainsKeywordsPredicate;
import seedu.address.model.person.AttributeContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.passenger.AddressContainsKeywordsPredicate;
import seedu.address.model.person.passenger.PriceIsGreaterThanAmountPredicate;
import seedu.address.model.tag.TagContainsKeywordsPredicate;


public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_namePrefixEmptyArg_throwsParseException() {
        assertParseFailure(parser,
                PREFIX_NAME + "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_multiPrefix_throwsParseException() {
        String userInput = FindCommand.COMMAND_WORD + " " + "n/Alice a/Bedok";
        assertParseFailure(parser, userInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_multiPriceArgs_throwsParseException() {
        String userInput = FindCommand.COMMAND_WORD + " " + "pr/100.00 pr/55";
        assertParseFailure(parser, userInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validNameArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Amy")));
        assertParseSuccess(parser, " n/Amy", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n n/Amy \n \t", expectedFindCommand);
    }

    @Test
    public void parse_validNameMultiArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Amy", "Bob")));
        assertParseSuccess(parser, " n/Amy n/Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n n/Amy n/Bob\n \t", expectedFindCommand);
    }

    @Test
    public void parse_validNameArgWithWhitespace_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Amy Jane")));
        assertParseSuccess(parser, " n/Amy Jane", expectedFindCommand);
    }

    @Test
    public void parse_validPhoneArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new PhoneContainsKeywordsPredicate(Arrays.asList(VALID_PHONE_AMY)));

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n " + PHONE_DESC_AMY + "\n \t", expectedFindCommand);

        assertParseSuccess(parser, PHONE_DESC_AMY, expectedFindCommand);
    }

    @Test
    public void parse_validAddressArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new AddressContainsKeywordsPredicate(Arrays.asList(VALID_ADDRESS_AMY)));

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n " + ADDRESS_DESC_AMY + "\n \t", expectedFindCommand);

        assertParseSuccess(parser, ADDRESS_DESC_AMY, expectedFindCommand);
    }

    @Test
    public void parse_validTagArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new TagContainsKeywordsPredicate(Arrays.asList("[" + VALID_TAG_IT + "]")));

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n " + TAG_DESC_FRIEND + "\n \t", expectedFindCommand);

        assertParseSuccess(parser, TAG_DESC_FRIEND, expectedFindCommand);
    }

    @Test
    public void parse_validPriceArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new PriceIsGreaterThanAmountPredicate(VALID_PRICE_BOB));

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n " + PRICE_DESC_BOB + "\n \t", expectedFindCommand);

        assertParseSuccess(parser, PRICE_DESC_BOB, expectedFindCommand);
    }

    @Test
    public void parse_validAllArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommandUsingName =
                new FindCommand(new AttributeContainsKeywordsPredicate(Collections.singletonList(VALID_NAME_BOB)));

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n " + PREFIX_ALL + VALID_NAME_BOB + "\n \t", expectedAllCommand);
        assertParseSuccess(parser, " " + PREFIX_ALL + VALID_NAME_BOB, expectedAllCommand);

        FindCommand expectedFindCommandUsingPhone =
                new FindCommand(new AttributeContainsKeywordsPredicate(Collections.singletonList(VALID_PHONE_AMY)));

        assertParseSuccess(parser, " \n " + PREFIX_ALL + VALID_PHONE_AMY + "\n \t",
                expectedFindCommandUsingPhone);
        assertParseSuccess(parser, " " + PREFIX_ALL + VALID_PHONE_AMY, expectedFindCommandUsingPhone);

        FindCommand expectedFindCommandUsingAddress =
                new FindCommand(new AttributeContainsKeywordsPredicate(Collections.singletonList(VALID_ADDRESS_AMY)));

        assertParseSuccess(parser, " \n " + PREFIX_ALL + VALID_ADDRESS_AMY + "\n \t",
                expectedFindCommandUsingAddress);
        assertParseSuccess(parser, " " + PREFIX_ALL + VALID_ADDRESS_AMY,
                expectedFindCommandUsingAddress);

        FindCommand expectedFindCommandUsingTag =
                new FindCommand(new AttributeContainsKeywordsPredicate(Collections.singletonList(VALID_TAG_IT)));

        assertParseSuccess(parser, " " + PREFIX_ALL + VALID_TAG_IT, expectedFindCommandUsingTag);
    }

    @Test
    public void parse_validDayArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new TripDayContainsKeywordsPredicate(
                        Collections.singletonList(VALID_TRIPDAY_BOB.toString())));

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n " + TRIPDAY_DESC_BOB + "\n \t", expectedFindCommand);

        assertParseSuccess(parser, TRIPDAY_DESC_BOB, expectedFindCommand);
    }

    @Test
    public void parse_validTripArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new TripTimeContainsKeywordsPredicate(Collections.singletonList("1930")));

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n " + TRIPTIME_DESC_BOB + "\n \t", expectedFindCommand);

        assertParseSuccess(parser, TRIPTIME_DESC_BOB, expectedFindCommand);
    }
}
