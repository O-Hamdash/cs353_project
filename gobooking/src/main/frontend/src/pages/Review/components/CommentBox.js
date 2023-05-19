import React, { Component } from "react";
import {StyleSheet, Text, View, TextInput } from "react-native";

/*
class CommentBox extends Component {
    constructor() {
        super();
        this.state = {
            textAreaValue: ""
        };
        this.handleChange = this.handleChange.bind(this);
    }
    handleChange(event) {
        this.setState({ textAreaValue: event.target.value });
    }
    render() {
        return (

            <div>
                <textarea
                    value={this.state.textAreaValue}
                    onChange={this.handleChange}
                    rows={5}
                    cols={50}
                    placeholder={"Write your comments here..."}
                />
            </div>
        );
    }
}
 */

const CommentBox = () => {
    const [text, onChangeText] = React.useState();

    return (
        <div className={"comment"}>
            <TextInput
                //onChangeText={onChangeText}
                value={text}
                placeholder={"Enter a comment for your review..."}
                placeholderTextColor={"grey"}
                style={styles.input}
                editable
                multiline
                numberOfLines={4}
                maxLength={100}
            />
        </div>
    );
};

const styles = StyleSheet.create({
    input: {
        borderColor: "gray",
        width: "100%",
        borderWidth: 1,
        borderRadius: 10,
        padding: 10,
    },
});
export default CommentBox;