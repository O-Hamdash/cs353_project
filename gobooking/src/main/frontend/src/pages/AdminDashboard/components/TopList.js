import * as React from 'react';
import List from '@mui/material/List';
import Divider from '@mui/material/Divider';


import house1 from "./images/house1.jpg";
import house2 from "./images/house2.jpg";
import house3 from "./images/house3.jpg";
import house4 from "./images/house4.jpg";
import house5 from "./images/house5.jpg";
import ScrollListElement from "./ScrollListElement";

function TopList(props) {
    return (
        <div className={"toplist-wrap"}>
            <h3>{props.title}</h3>
            <div className={"toplist--ext"}>
                <div className={"toplist"}>
                    <List sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}>
                        <ScrollListElement
                            alt={"house1"}
                            src={house1}
                            primary={"Cankaya, Ankara"}
                            value={5}
                            owner={"Ahmet Arda Ceylan"}
                        />
                        <Divider variant="inset" component="li" />
                        <ScrollListElement
                            alt={"house2"}
                            src={house2}
                            primary={"Bursa"}
                            value={5}
                            owner={"Mahmut Furkan Gon"}
                        />
                        <Divider variant="inset" component="li" />
                        <ScrollListElement
                            alt={"house3"}
                            src={house3}
                            primary={"Amasya"}
                            value={4}
                            owner={"Omer Kagan Danaci"}
                        />
                        <Divider variant="inset" component="li" />
                        <ScrollListElement
                            alt={"house4"}
                            src={house4}
                            primary={"Samsun"}
                            value={4}
                            owner={"Mustafa Yetgin"}
                        />
                        <Divider variant="inset" component="li" />
                        <ScrollListElement
                            alt={"house5"}
                            src={house5}
                            primary={"Ankara"}
                            value={3}
                            owner={"Omar Hamdache"}
                        />
                    </List>
                </div>
            </div>
        </div>
    );
}

export default TopList;