import React from 'react';
import { StyleSheet, Text, View, TextInput } from 'react-native';

const TitleBox = () => {
    const [text, onChangeText] = React.useState();

    return (
        <div className={"title"}>
            <TextInput
                //onChangeText={onChangeText}
                value={text}
                placeholder={"Enter a title for your review..."}
                placeholderTextColor={"grey"}
                style={styles.input}
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

export default TitleBox;