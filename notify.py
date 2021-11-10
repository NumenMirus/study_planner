import csv
from datetime import datetime
from plyer import notification
from time import sleep

class argument:
    name = ""
    id = 0
    priority = 0

    def __init__(self):
        self.name = "Lorem Ipsum"
        self.id = 9999999
        self.priority = 9999999

    def __init__(self, name, id, priority):
        self.name = name
        self.id = id
        self.priority = priority

    def __str__(self) -> str:
        return "Name: %s\t\t ID: %d\t Priority: %d" %(self.name, self.id, self.priority)

    def checkId(self, id):
        return self.id == id
    
    def getName(self):
        return self.name

    def getPriority(self):
        return self.priority
    
    def isMoreImportant(self, p):
        return self.priority > p

def notify(text):
    notification.notify(
    title="Ripassi odierni:\n\n",
    message=text,
    app_icon="/icons/studying.ico",
    timeout=20
    )

calendar = open("calendar.csv", mode="r")
calendar = csv.reader(calendar)

data = open("data.csv", mode="r")
data = csv.reader(data)

argumentList = []
for row in data:
    argumentList.append(argument(row[5], int(row[0]), int(row[4])))

todayDate = datetime.now()

notificationText = ""
while True:
    for row in calendar:
        day, month, year = (row[1].split("/"))
        calDate = datetime(int(year), int(month), int(day))
        
        if todayDate.year == calDate.year and todayDate.month == calDate.month and todayDate.day == calDate.day:
            tempList= []
            for arg in argumentList:
                if arg.checkId(int(row[0])):
                    notificationText = notificationText + "%-25spriorità: %d\n" %(arg.getName(), arg.getPriority())

    notify(notificationText)
    sleep(10)