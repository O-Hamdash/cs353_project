import React from "react";
import ListItemAvatar from "@mui/material/ListItemAvatar";
import Avatar from "@mui/material/Avatar";
import ListItemText from "@mui/material/ListItemText";
import Typography from "@mui/material/Typography";
import ListItem from "@mui/material/ListItem";
import StaticReviewStars from "./StaticReviewStars";

function ScrollListElement(props){
    return (
        <ListItem alignItems="flex-start" disablePadding={true}>
            <ListItemAvatar>
                <Avatar alt={props.alt} src={props.src} />
            </ListItemAvatar>
            <ListItemText
                primary={props.primary}
                secondary={
                    <React.Fragment>
                        <Typography
                            sx={{ display: 'inline' }}
                            component="span"
                            variant="body2"
                            color="text.primary"
                        >
                            <StaticReviewStars value={props.value} />
                        </Typography>
                        {"\nOwned by "}
                        <b>{props.owner}</b>
                    </React.Fragment>
                }
            />
        </ListItem>
    );
}

export default ScrollListElement;