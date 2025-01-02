package com.pim.planta.db;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.pim.planta.models.Calendar;
import com.pim.planta.models.Converters;
import com.pim.planta.models.DiaryEntry;
import com.pim.planta.models.Plant;
import com.pim.planta.models.User;
import com.pim.planta.models.UserPlantRelation;
import java.lang.Class;
import java.lang.Override;
import java.lang.Runnable;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class DAO_Impl implements DAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Plant> __insertionAdapterOfPlant;

  private final EntityInsertionAdapter<DiaryEntry> __insertionAdapterOfDiaryEntry;

  private final EntityInsertionAdapter<User> __insertionAdapterOfUser;

  private final EntityInsertionAdapter<Calendar> __insertionAdapterOfCalendar;

  private final EntityInsertionAdapter<UserPlantRelation> __insertionAdapterOfUserPlantRelation;

  private final EntityInsertionAdapter<UserPlantRelation> __insertionAdapterOfUserPlantRelation_1;

  private final EntityDeletionOrUpdateAdapter<Plant> __deletionAdapterOfPlant;

  private final EntityDeletionOrUpdateAdapter<DiaryEntry> __deletionAdapterOfDiaryEntry;

  private final EntityDeletionOrUpdateAdapter<User> __deletionAdapterOfUser;

  private final EntityDeletionOrUpdateAdapter<Calendar> __deletionAdapterOfCalendar;

  private final EntityDeletionOrUpdateAdapter<UserPlantRelation> __deletionAdapterOfUserPlantRelation;

  private final EntityDeletionOrUpdateAdapter<Plant> __updateAdapterOfPlant;

  private final EntityDeletionOrUpdateAdapter<DiaryEntry> __updateAdapterOfDiaryEntry;

  private final EntityDeletionOrUpdateAdapter<User> __updateAdapterOfUser;

  private final EntityDeletionOrUpdateAdapter<Calendar> __updateAdapterOfCalendar;

  private final EntityDeletionOrUpdateAdapter<UserPlantRelation> __updateAdapterOfUserPlantRelation;

  private final SharedSQLiteStatement __preparedStmtOfIncrementGrowCount;

  private final SharedSQLiteStatement __preparedStmtOfDecrementGrowCount;

  public DAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPlant = new EntityInsertionAdapter<Plant>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `plants` (`id`,`name`,`basePath`,`imageResourceId`,`xp`,`xpMax`,`description`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Plant value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getBasePath() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getBasePath());
        }
        stmt.bindLong(4, value.getImageResourceId());
        stmt.bindLong(5, value.getXp());
        stmt.bindLong(6, value.getXpMax());
        if (value.getDescription() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getDescription());
        }
      }
    };
    this.__insertionAdapterOfDiaryEntry = new EntityInsertionAdapter<DiaryEntry>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `diary-entries` (`id`,`highlight`,`annotation`,`emotion`,`user_id`,`date`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DiaryEntry value) {
        stmt.bindLong(1, value.getId());
        if (value.getHighlight() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getHighlight());
        }
        if (value.getAnnotation() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getAnnotation());
        }
        stmt.bindLong(4, value.getEmotion());
        stmt.bindLong(5, value.getUser_id());
        stmt.bindLong(6, value.getDate());
      }
    };
    this.__insertionAdapterOfUser = new EntityInsertionAdapter<User>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `users` (`id`,`username`,`password`,`email`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, User value) {
        stmt.bindLong(1, value.getId());
        if (value.getUsername() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getUsername());
        }
        if (value.getPassword() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getPassword());
        }
        if (value.getEmail() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getEmail());
        }
      }
    };
    this.__insertionAdapterOfCalendar = new EntityInsertionAdapter<Calendar>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `calendar` (`id`,`fechas`,`anotaciones`,`emociones`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Calendar value) {
        stmt.bindLong(1, value.getId());
        final String _tmp = Converters.fromDateList(value.getFechas());
        if (_tmp == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, _tmp);
        }
        final String _tmp_1 = Converters.fromListToString(value.getAnotaciones());
        if (_tmp_1 == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, _tmp_1);
        }
        stmt.bindLong(4, value.getEmociones());
      }
    };
    this.__insertionAdapterOfUserPlantRelation = new EntityInsertionAdapter<UserPlantRelation>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `user_plant_relation` (`userId`,`plantId`,`growCount`) VALUES (?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, UserPlantRelation value) {
        stmt.bindLong(1, value.userId);
        stmt.bindLong(2, value.plantId);
        stmt.bindLong(3, value.growCount);
      }
    };
    this.__insertionAdapterOfUserPlantRelation_1 = new EntityInsertionAdapter<UserPlantRelation>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `user_plant_relation` (`userId`,`plantId`,`growCount`) VALUES (?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, UserPlantRelation value) {
        stmt.bindLong(1, value.userId);
        stmt.bindLong(2, value.plantId);
        stmt.bindLong(3, value.growCount);
      }
    };
    this.__deletionAdapterOfPlant = new EntityDeletionOrUpdateAdapter<Plant>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `plants` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Plant value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__deletionAdapterOfDiaryEntry = new EntityDeletionOrUpdateAdapter<DiaryEntry>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `diary-entries` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DiaryEntry value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__deletionAdapterOfUser = new EntityDeletionOrUpdateAdapter<User>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `users` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, User value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__deletionAdapterOfCalendar = new EntityDeletionOrUpdateAdapter<Calendar>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `calendar` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Calendar value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__deletionAdapterOfUserPlantRelation = new EntityDeletionOrUpdateAdapter<UserPlantRelation>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `user_plant_relation` WHERE `userId` = ? AND `plantId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, UserPlantRelation value) {
        stmt.bindLong(1, value.userId);
        stmt.bindLong(2, value.plantId);
      }
    };
    this.__updateAdapterOfPlant = new EntityDeletionOrUpdateAdapter<Plant>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `plants` SET `id` = ?,`name` = ?,`basePath` = ?,`imageResourceId` = ?,`xp` = ?,`xpMax` = ?,`description` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Plant value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getBasePath() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getBasePath());
        }
        stmt.bindLong(4, value.getImageResourceId());
        stmt.bindLong(5, value.getXp());
        stmt.bindLong(6, value.getXpMax());
        if (value.getDescription() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getDescription());
        }
        stmt.bindLong(8, value.getId());
      }
    };
    this.__updateAdapterOfDiaryEntry = new EntityDeletionOrUpdateAdapter<DiaryEntry>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `diary-entries` SET `id` = ?,`highlight` = ?,`annotation` = ?,`emotion` = ?,`user_id` = ?,`date` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DiaryEntry value) {
        stmt.bindLong(1, value.getId());
        if (value.getHighlight() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getHighlight());
        }
        if (value.getAnnotation() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getAnnotation());
        }
        stmt.bindLong(4, value.getEmotion());
        stmt.bindLong(5, value.getUser_id());
        stmt.bindLong(6, value.getDate());
        stmt.bindLong(7, value.getId());
      }
    };
    this.__updateAdapterOfUser = new EntityDeletionOrUpdateAdapter<User>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `users` SET `id` = ?,`username` = ?,`password` = ?,`email` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, User value) {
        stmt.bindLong(1, value.getId());
        if (value.getUsername() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getUsername());
        }
        if (value.getPassword() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getPassword());
        }
        if (value.getEmail() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getEmail());
        }
        stmt.bindLong(5, value.getId());
      }
    };
    this.__updateAdapterOfCalendar = new EntityDeletionOrUpdateAdapter<Calendar>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `calendar` SET `id` = ?,`fechas` = ?,`anotaciones` = ?,`emociones` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Calendar value) {
        stmt.bindLong(1, value.getId());
        final String _tmp = Converters.fromDateList(value.getFechas());
        if (_tmp == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, _tmp);
        }
        final String _tmp_1 = Converters.fromListToString(value.getAnotaciones());
        if (_tmp_1 == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, _tmp_1);
        }
        stmt.bindLong(4, value.getEmociones());
        stmt.bindLong(5, value.getId());
      }
    };
    this.__updateAdapterOfUserPlantRelation = new EntityDeletionOrUpdateAdapter<UserPlantRelation>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `user_plant_relation` SET `userId` = ?,`plantId` = ?,`growCount` = ? WHERE `userId` = ? AND `plantId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, UserPlantRelation value) {
        stmt.bindLong(1, value.userId);
        stmt.bindLong(2, value.plantId);
        stmt.bindLong(3, value.growCount);
        stmt.bindLong(4, value.userId);
        stmt.bindLong(5, value.plantId);
      }
    };
    this.__preparedStmtOfIncrementGrowCount = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE user_plant_relation SET growCount = growCount + 1 WHERE userId = ? AND plantId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDecrementGrowCount = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE user_plant_relation SET growCount = growCount - 1 WHERE userId = ? AND plantId = ?";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Plant planta) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfPlant.insert(planta);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insert(final DiaryEntry entrada) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfDiaryEntry.insert(entrada);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insert(final User usuario) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfUser.insert(usuario);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insert(final Calendar calendario) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCalendar.insert(calendario);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insert(final UserPlantRelation user_plant_relation) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfUserPlantRelation.insert(user_plant_relation);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertUserPlantRelation(final UserPlantRelation relation) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfUserPlantRelation_1.insert(relation);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Plant planta) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfPlant.handle(planta);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final DiaryEntry entrada) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfDiaryEntry.handle(entrada);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final User usuario) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfUser.handle(usuario);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Calendar calendario) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfCalendar.handle(calendario);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final UserPlantRelation user_plant_relation) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfUserPlantRelation.handle(user_plant_relation);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Plant planta) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfPlant.handle(planta);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final DiaryEntry entrada) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfDiaryEntry.handle(entrada);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final User usuario) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfUser.handle(usuario);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Calendar calendario) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfCalendar.handle(calendario);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final UserPlantRelation user_plant_relation) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfUserPlantRelation.handle(user_plant_relation);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void commit(final Runnable operations) {
    __db.beginTransaction();
    try {
      DAO.super.commit(operations);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void incrementGrowCount(final int userId, final int plantId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfIncrementGrowCount.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, userId);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, plantId);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfIncrementGrowCount.release(_stmt);
    }
  }

  @Override
  public void decrementGrowCount(final int userId, final int plantId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDecrementGrowCount.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, userId);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, plantId);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDecrementGrowCount.release(_stmt);
    }
  }

  @Override
  public List<Plant> getAllPlantas() {
    final String _sql = "SELECT * FROM plants";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfBasePath = CursorUtil.getColumnIndexOrThrow(_cursor, "basePath");
      final int _cursorIndexOfImageResourceId = CursorUtil.getColumnIndexOrThrow(_cursor, "imageResourceId");
      final int _cursorIndexOfXp = CursorUtil.getColumnIndexOrThrow(_cursor, "xp");
      final int _cursorIndexOfXpMax = CursorUtil.getColumnIndexOrThrow(_cursor, "xpMax");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final List<Plant> _result = new ArrayList<Plant>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Plant _item;
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final String _tmpBasePath;
        if (_cursor.isNull(_cursorIndexOfBasePath)) {
          _tmpBasePath = null;
        } else {
          _tmpBasePath = _cursor.getString(_cursorIndexOfBasePath);
        }
        final int _tmpImageResourceId;
        _tmpImageResourceId = _cursor.getInt(_cursorIndexOfImageResourceId);
        final int _tmpXp;
        _tmpXp = _cursor.getInt(_cursorIndexOfXp);
        final int _tmpXpMax;
        _tmpXpMax = _cursor.getInt(_cursorIndexOfXpMax);
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        _item = new Plant(_tmpName,_tmpBasePath,_tmpImageResourceId,_tmpXp,_tmpXpMax,_tmpDescription);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<DiaryEntry> getAllEntries() {
    final String _sql = "SELECT * FROM `diary-entries`";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfHighlight = CursorUtil.getColumnIndexOrThrow(_cursor, "highlight");
      final int _cursorIndexOfAnnotation = CursorUtil.getColumnIndexOrThrow(_cursor, "annotation");
      final int _cursorIndexOfEmotion = CursorUtil.getColumnIndexOrThrow(_cursor, "emotion");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final List<DiaryEntry> _result = new ArrayList<DiaryEntry>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final DiaryEntry _item;
        final String _tmpHighlight;
        if (_cursor.isNull(_cursorIndexOfHighlight)) {
          _tmpHighlight = null;
        } else {
          _tmpHighlight = _cursor.getString(_cursorIndexOfHighlight);
        }
        final String _tmpAnnotation;
        if (_cursor.isNull(_cursorIndexOfAnnotation)) {
          _tmpAnnotation = null;
        } else {
          _tmpAnnotation = _cursor.getString(_cursorIndexOfAnnotation);
        }
        final int _tmpEmotion;
        _tmpEmotion = _cursor.getInt(_cursorIndexOfEmotion);
        final int _tmpUser_id;
        _tmpUser_id = _cursor.getInt(_cursorIndexOfUserId);
        final long _tmpDate;
        _tmpDate = _cursor.getLong(_cursorIndexOfDate);
        _item = new DiaryEntry(_tmpHighlight,_tmpAnnotation,_tmpEmotion,_tmpUser_id,_tmpDate);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<User> getAllUsuarios() {
    final String _sql = "SELECT * FROM users";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
      final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
      final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
      final List<User> _result = new ArrayList<User>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final User _item;
        final String _tmpUsername;
        if (_cursor.isNull(_cursorIndexOfUsername)) {
          _tmpUsername = null;
        } else {
          _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
        }
        final String _tmpPassword;
        if (_cursor.isNull(_cursorIndexOfPassword)) {
          _tmpPassword = null;
        } else {
          _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        }
        final String _tmpEmail;
        if (_cursor.isNull(_cursorIndexOfEmail)) {
          _tmpEmail = null;
        } else {
          _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
        }
        _item = new User(_tmpUsername,_tmpEmail,_tmpPassword);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Calendar> getAllCalendar() {
    final String _sql = "SELECT * FROM calendar";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfFechas = CursorUtil.getColumnIndexOrThrow(_cursor, "fechas");
      final int _cursorIndexOfAnotaciones = CursorUtil.getColumnIndexOrThrow(_cursor, "anotaciones");
      final int _cursorIndexOfEmociones = CursorUtil.getColumnIndexOrThrow(_cursor, "emociones");
      final List<Calendar> _result = new ArrayList<Calendar>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Calendar _item;
        final List<Date> _tmpFechas;
        final String _tmp;
        if (_cursor.isNull(_cursorIndexOfFechas)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getString(_cursorIndexOfFechas);
        }
        _tmpFechas = Converters.toDateList(_tmp);
        final List<String> _tmpAnotaciones;
        final String _tmp_1;
        if (_cursor.isNull(_cursorIndexOfAnotaciones)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getString(_cursorIndexOfAnotaciones);
        }
        _tmpAnotaciones = Converters.fromStringToList(_tmp_1);
        final int _tmpEmociones;
        _tmpEmociones = _cursor.getInt(_cursorIndexOfEmociones);
        _item = new Calendar(_tmpFechas,_tmpAnotaciones,_tmpEmociones);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Plant getPlantaById(final int id) {
    final String _sql = "SELECT * FROM plants WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfBasePath = CursorUtil.getColumnIndexOrThrow(_cursor, "basePath");
      final int _cursorIndexOfImageResourceId = CursorUtil.getColumnIndexOrThrow(_cursor, "imageResourceId");
      final int _cursorIndexOfXp = CursorUtil.getColumnIndexOrThrow(_cursor, "xp");
      final int _cursorIndexOfXpMax = CursorUtil.getColumnIndexOrThrow(_cursor, "xpMax");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final Plant _result;
      if(_cursor.moveToFirst()) {
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final String _tmpBasePath;
        if (_cursor.isNull(_cursorIndexOfBasePath)) {
          _tmpBasePath = null;
        } else {
          _tmpBasePath = _cursor.getString(_cursorIndexOfBasePath);
        }
        final int _tmpImageResourceId;
        _tmpImageResourceId = _cursor.getInt(_cursorIndexOfImageResourceId);
        final int _tmpXp;
        _tmpXp = _cursor.getInt(_cursorIndexOfXp);
        final int _tmpXpMax;
        _tmpXpMax = _cursor.getInt(_cursorIndexOfXpMax);
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        _result = new Plant(_tmpName,_tmpBasePath,_tmpImageResourceId,_tmpXp,_tmpXpMax,_tmpDescription);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Plant getPlantaByName(final String name) {
    final String _sql = "SELECT * FROM plants WHERE name = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (name == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, name);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfBasePath = CursorUtil.getColumnIndexOrThrow(_cursor, "basePath");
      final int _cursorIndexOfImageResourceId = CursorUtil.getColumnIndexOrThrow(_cursor, "imageResourceId");
      final int _cursorIndexOfXp = CursorUtil.getColumnIndexOrThrow(_cursor, "xp");
      final int _cursorIndexOfXpMax = CursorUtil.getColumnIndexOrThrow(_cursor, "xpMax");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final Plant _result;
      if(_cursor.moveToFirst()) {
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final String _tmpBasePath;
        if (_cursor.isNull(_cursorIndexOfBasePath)) {
          _tmpBasePath = null;
        } else {
          _tmpBasePath = _cursor.getString(_cursorIndexOfBasePath);
        }
        final int _tmpImageResourceId;
        _tmpImageResourceId = _cursor.getInt(_cursorIndexOfImageResourceId);
        final int _tmpXp;
        _tmpXp = _cursor.getInt(_cursorIndexOfXp);
        final int _tmpXpMax;
        _tmpXpMax = _cursor.getInt(_cursorIndexOfXpMax);
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        _result = new Plant(_tmpName,_tmpBasePath,_tmpImageResourceId,_tmpXp,_tmpXpMax,_tmpDescription);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public DiaryEntry getEntradaById(final int id) {
    final String _sql = "SELECT * FROM `diary-entries` WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfHighlight = CursorUtil.getColumnIndexOrThrow(_cursor, "highlight");
      final int _cursorIndexOfAnnotation = CursorUtil.getColumnIndexOrThrow(_cursor, "annotation");
      final int _cursorIndexOfEmotion = CursorUtil.getColumnIndexOrThrow(_cursor, "emotion");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final DiaryEntry _result;
      if(_cursor.moveToFirst()) {
        final String _tmpHighlight;
        if (_cursor.isNull(_cursorIndexOfHighlight)) {
          _tmpHighlight = null;
        } else {
          _tmpHighlight = _cursor.getString(_cursorIndexOfHighlight);
        }
        final String _tmpAnnotation;
        if (_cursor.isNull(_cursorIndexOfAnnotation)) {
          _tmpAnnotation = null;
        } else {
          _tmpAnnotation = _cursor.getString(_cursorIndexOfAnnotation);
        }
        final int _tmpEmotion;
        _tmpEmotion = _cursor.getInt(_cursorIndexOfEmotion);
        final int _tmpUser_id;
        _tmpUser_id = _cursor.getInt(_cursorIndexOfUserId);
        final long _tmpDate;
        _tmpDate = _cursor.getLong(_cursorIndexOfDate);
        _result = new DiaryEntry(_tmpHighlight,_tmpAnnotation,_tmpEmotion,_tmpUser_id,_tmpDate);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<DiaryEntry> getEntradasByUserId(final int id) {
    final String _sql = "SELECT * FROM `diary-entries` WHERE user_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfHighlight = CursorUtil.getColumnIndexOrThrow(_cursor, "highlight");
      final int _cursorIndexOfAnnotation = CursorUtil.getColumnIndexOrThrow(_cursor, "annotation");
      final int _cursorIndexOfEmotion = CursorUtil.getColumnIndexOrThrow(_cursor, "emotion");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final List<DiaryEntry> _result = new ArrayList<DiaryEntry>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final DiaryEntry _item;
        final String _tmpHighlight;
        if (_cursor.isNull(_cursorIndexOfHighlight)) {
          _tmpHighlight = null;
        } else {
          _tmpHighlight = _cursor.getString(_cursorIndexOfHighlight);
        }
        final String _tmpAnnotation;
        if (_cursor.isNull(_cursorIndexOfAnnotation)) {
          _tmpAnnotation = null;
        } else {
          _tmpAnnotation = _cursor.getString(_cursorIndexOfAnnotation);
        }
        final int _tmpEmotion;
        _tmpEmotion = _cursor.getInt(_cursorIndexOfEmotion);
        final int _tmpUser_id;
        _tmpUser_id = _cursor.getInt(_cursorIndexOfUserId);
        final long _tmpDate;
        _tmpDate = _cursor.getLong(_cursorIndexOfDate);
        _item = new DiaryEntry(_tmpHighlight,_tmpAnnotation,_tmpEmotion,_tmpUser_id,_tmpDate);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public DiaryEntry getEntradaByUserIdAndDate(final int userId, final long date) {
    final String _sql = "SELECT * FROM `diary-entries` WHERE user_id = ? AND date = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, date);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfHighlight = CursorUtil.getColumnIndexOrThrow(_cursor, "highlight");
      final int _cursorIndexOfAnnotation = CursorUtil.getColumnIndexOrThrow(_cursor, "annotation");
      final int _cursorIndexOfEmotion = CursorUtil.getColumnIndexOrThrow(_cursor, "emotion");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final DiaryEntry _result;
      if(_cursor.moveToFirst()) {
        final String _tmpHighlight;
        if (_cursor.isNull(_cursorIndexOfHighlight)) {
          _tmpHighlight = null;
        } else {
          _tmpHighlight = _cursor.getString(_cursorIndexOfHighlight);
        }
        final String _tmpAnnotation;
        if (_cursor.isNull(_cursorIndexOfAnnotation)) {
          _tmpAnnotation = null;
        } else {
          _tmpAnnotation = _cursor.getString(_cursorIndexOfAnnotation);
        }
        final int _tmpEmotion;
        _tmpEmotion = _cursor.getInt(_cursorIndexOfEmotion);
        final int _tmpUser_id;
        _tmpUser_id = _cursor.getInt(_cursorIndexOfUserId);
        final long _tmpDate;
        _tmpDate = _cursor.getLong(_cursorIndexOfDate);
        _result = new DiaryEntry(_tmpHighlight,_tmpAnnotation,_tmpEmotion,_tmpUser_id,_tmpDate);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public User getUsuarioById(final int id) {
    final String _sql = "SELECT * FROM users WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
      final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
      final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
      final User _result;
      if(_cursor.moveToFirst()) {
        final String _tmpUsername;
        if (_cursor.isNull(_cursorIndexOfUsername)) {
          _tmpUsername = null;
        } else {
          _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
        }
        final String _tmpPassword;
        if (_cursor.isNull(_cursorIndexOfPassword)) {
          _tmpPassword = null;
        } else {
          _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        }
        final String _tmpEmail;
        if (_cursor.isNull(_cursorIndexOfEmail)) {
          _tmpEmail = null;
        } else {
          _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
        }
        _result = new User(_tmpUsername,_tmpEmail,_tmpPassword);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Calendar getCalendarById(final int id) {
    final String _sql = "SELECT * FROM calendar WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfFechas = CursorUtil.getColumnIndexOrThrow(_cursor, "fechas");
      final int _cursorIndexOfAnotaciones = CursorUtil.getColumnIndexOrThrow(_cursor, "anotaciones");
      final int _cursorIndexOfEmociones = CursorUtil.getColumnIndexOrThrow(_cursor, "emociones");
      final Calendar _result;
      if(_cursor.moveToFirst()) {
        final List<Date> _tmpFechas;
        final String _tmp;
        if (_cursor.isNull(_cursorIndexOfFechas)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getString(_cursorIndexOfFechas);
        }
        _tmpFechas = Converters.toDateList(_tmp);
        final List<String> _tmpAnotaciones;
        final String _tmp_1;
        if (_cursor.isNull(_cursorIndexOfAnotaciones)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getString(_cursorIndexOfAnotaciones);
        }
        _tmpAnotaciones = Converters.fromStringToList(_tmp_1);
        final int _tmpEmociones;
        _tmpEmociones = _cursor.getInt(_cursorIndexOfEmociones);
        _result = new Calendar(_tmpFechas,_tmpAnotaciones,_tmpEmociones);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public User getUserByEmail(final String email) {
    final String _sql = "SELECT * FROM users WHERE email = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (email == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, email);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
      final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
      final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
      final User _result;
      if(_cursor.moveToFirst()) {
        final String _tmpUsername;
        if (_cursor.isNull(_cursorIndexOfUsername)) {
          _tmpUsername = null;
        } else {
          _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
        }
        final String _tmpPassword;
        if (_cursor.isNull(_cursorIndexOfPassword)) {
          _tmpPassword = null;
        } else {
          _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        }
        final String _tmpEmail;
        if (_cursor.isNull(_cursorIndexOfEmail)) {
          _tmpEmail = null;
        } else {
          _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
        }
        _result = new User(_tmpUsername,_tmpEmail,_tmpPassword);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<UserPlantRelation> getUserPlantRelations(final int userId) {
    final String _sql = "SELECT * FROM user_plant_relation WHERE userId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final int _cursorIndexOfPlantId = CursorUtil.getColumnIndexOrThrow(_cursor, "plantId");
      final int _cursorIndexOfGrowCount = CursorUtil.getColumnIndexOrThrow(_cursor, "growCount");
      final List<UserPlantRelation> _result = new ArrayList<UserPlantRelation>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final UserPlantRelation _item;
        final int _tmpUserId;
        _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
        final int _tmpPlantId;
        _tmpPlantId = _cursor.getInt(_cursorIndexOfPlantId);
        _item = new UserPlantRelation(_tmpUserId,_tmpPlantId);
        _item.growCount = _cursor.getInt(_cursorIndexOfGrowCount);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getGrowCount(final int userId, final int plantId) {
    final String _sql = "SELECT growCount FROM user_plant_relation WHERE userId = ? AND plantId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, plantId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
