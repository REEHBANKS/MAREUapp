package com.banks.mareu.model;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
     * Model object representing a Meeting
     */

    public class Meeting {


        /** Identifier */
        private long id;

        /** Meeting name */
        private String meetingName;

        /** Room name */
        private Room room;

        /** Mail */
        private String mail;

        /** Date */
        private Calendar date;





        /** Constructor*/

        public Meeting (long id, String meetingName, Room roomName, String mail, Calendar date ) {
            this.id = id;
            this.meetingName = meetingName;
            this.room = roomName;
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

        public Room getRoom() { return room;
        }

        public void setRoom(Room room) { this.room = room;
        }

        public String getMail() { return mail;
        }

        public void setMail(String mail) { this.mail = mail;
        }

        public Calendar getDate() { return date;
        }

        public void setDate(Calendar date) { this.date = date;
        }

        public String getStringDate (){
            SimpleDateFormat sdf = new SimpleDateFormat( "HH:mm", Locale.FRANCE);
            return sdf.format(date.getTime());

     }






}
