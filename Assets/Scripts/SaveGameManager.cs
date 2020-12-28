using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Runtime.Serialization.Formatters.Binary;
using UnityEngine;
using System;
using Debug = UnityEngine.Debug;

public class SaveGameManager : MonoBehaviour
{
    public static SaveGameManager instance;
    public static string persistentDataPath;
    public Character character;

    

    void Awake()
    {
        if (instance == null)
        {
            instance = this;
        }
    }

    public bool IsSaveFile()
    {
        return Directory.Exists(Application.persistentDataPath + "game_save");
    }

    public void SaveGame()
    {
        BinaryFormatter bf = new BinaryFormatter();
        if (!IsSaveFile())
        {
            Directory.CreateDirectory(Application.persistentDataPath + "/game_save");
        }
        if (!Directory.Exists(Application.persistentDataPath + "/game_save/character_status"))
        {
            Directory.CreateDirectory(Application.persistentDataPath + "/game_save/character_status");
        }

        FileStream file = File.Create(Application.persistentDataPath + "/game_save/character_status/save.gd");

        character.PositionX = transform.position.x;
        character.PositionY = transform.position.y;
        character.PositionZ = transform.position.z;
        Debug.Log(character.lives);
        var json = JsonUtility.ToJson(character);
        bf.Serialize(file, json);
        file.Close();
    }

    public void LoadGame()
    {
        
        if (!Directory.Exists(Application.persistentDataPath + "/game_save/character_status"))
        {
            Directory.CreateDirectory(Application.persistentDataPath + "/game_save/character_status");
        }
        if (File.Exists(Application.persistentDataPath + "/game_save/character_status/save.gd"))
        {
            BinaryFormatter bf = new BinaryFormatter();
            FileStream file = File.Open(Application.persistentDataPath + "/game_save/character_status/save.gd", FileMode.Open);
            JsonUtility.FromJsonOverwrite((string)bf.Deserialize(file), character);
            Debug.Log(character.lives);
            file.Close();
        }
    }
}