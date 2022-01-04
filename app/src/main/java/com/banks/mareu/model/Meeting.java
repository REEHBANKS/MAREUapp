package com.banks.mareu.model;


import java.util.Calendar;

/**
     * Model object representing a Meeting
     */

    public class Meeting {


        /** Identifier */
        private long id;

        /** Meeting name */
        private String meetingName;

        /** Room name */
        private String roomName;

        /** Room color */
        private String roomColor;

        /** Mail */
        private String mail;

        /** Date */
        private Calendar date;



        /** Constructor*/

        public Meeting (long id, String meetingName, String roomName, String roomColor, String mail, Calendar date ) {
            this.id = id;
            this.meetingName = meetingName;
            this.roomName = roomName;
            this.roomColor = roomColor;
            this.mail = mail;
            this.date = date;
        }

        public long getId() { return id;
        }

        public void setId(long id) { this.id = id;
        }

        public String getMeetingName() { return meetingName;
        }

        public void setMeetingName(String meetingName) { this.meetingName = meetingName;
        }

        public String getRoomName() { return roomName;
        }

        public void setRoomName(String roomName) { this.roomName = roomName;
        }

        public String getRoomColor() { return roomColor;
        }

        public void setRoomColor(String roomColor) { this.roomColor = roomColor;
        }

        public String getMail() { return mail;
        }

        public void setMail(String mail) { this.mail = mail;
        }

        public Calendar getDate() { return date;
        }

        public void setDate(Calendar date) { this.date = date;
        }

        //public String getStringDate (){}






    }
