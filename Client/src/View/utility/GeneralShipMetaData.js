/**
 * Created by Silvia on 20/04/2017.
 */

var GeneralShip={

    "numberOfShip":4,
    "Ship":[
        {

            "ShipName": "Trireme",
            "Dimension": 3,
            "Img": "../placeShipUI/img/ship3.png",
            "Weight": 20,
            "Istance":[
                {
                    "ShipID":1,
                    "Position":{
                        "x":3,
                        "y":5
                    },
                    "Orientation":0,
                    "Weapon":[
                        {
                            "WeaponName":"W1",
                            "TimeReload":0,
                            "Ammo":"infinity",
                            "Img":"../playGameUI/img/w1.png"
                        },
                        {
                            "WeaponName":"W2",
                            "TimeReload":2,
                            "Ammo":3,
                            "Img":"../playGameUI/img/w2.png"

                        }
                    ]

                },
                {
                    "ShipID":2,
                    "Dimension":3,
                    "Position":{
                        "x":2,
                        "y":1
                    },
                    "Orientation":270,
                    "Weapon":[
                        {
                            "WeaponName":"W1",
                            "TimeReload":0,
                            "Ammo":"infinity",
                            "Img":"../playGameUI/img/w1.png"

                        },
                        {
                            "WeaponName":"W2",
                            "TimeReload":3,
                            "Ammo":2,
                            "Img":"../playGameUI/img/w2.png"

                        }
                    ]

                },
                {
                    "ShipID":3,
                    "Dimension":3,
                    "Position":{
                        "x":5,
                        "y":1
                    },
                    "Orientation":0,
                    "Weapon":[
                        {
                            "WeaponName":"W1",
                            "TimeReload":0,
                            "Ammo":"infinity",
                            "Img":"../playGameUI/img/w1.png"

                        },
                        {
                            "WeaponName":"W2",
                            "TimeReload":2,
                            "Ammo":3,
                            "Img":"../playGameUI/img/w2.png"

                        }
                    ]

                }

            ]
        },
        {
            "ShipName": "Quadrireme",
            "Dimension": 4,
            "Img": "../placeShipUI/img/ship4.png",
            "Weight": 40,
            "Istance":[
                {
                    "ShipID":4,
                    "Dimension":4,
                    "Position":{
                        "x":7,
                        "y":7
                    },
                    "Orientation":90,
                    "Weapon":[
                        {
                            "WeaponName":"W3",
                            "TimeReload":0,
                            "Ammo":"infinity",
                            "Img":"../playGameUI/img/w3.png"

                        },
                        {
                            "WeaponName":"W4",
                            "TimeReload":3,
                            "Ammo":2,
                            "Img":"../playGameUI/img/w3.png"

                        }
                    ]
                }
            ]

}
    ]

};
