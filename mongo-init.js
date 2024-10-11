db.createUser({
    user: "daksh",
    pwd: "daksh1",
    roles: [
      {
        role: "dbOwner",
        db: "BookStore"
      }
    ]
  });
  