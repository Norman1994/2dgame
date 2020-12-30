using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System;
using UnityEngine.SceneManagement;

[Serializable]
public class Character : Unit
{
    [SerializeField]
    public int lives = 5;

    [SerializeField]
    public int chances = 3;

    public float PositionX, PositionY, PositionZ;

    public bool entering;

    public static string status;

    public AudioClip MyAudio;
    public AudioClip secondVoice;
    public AudioClip checkpointVoice;
    public AudioClip enemyVoice;
    public AudioClip questVoice;
    public AudioClip bossVoice;
    public AudioClip shootSound;
    public AudioClip finalSound;
    public AudioClip painSound;
    AudioSource audioSource;

    private bool boss;
    private bool ground;
    private bool shootUp;

    private bool isShooting;

    public static bool quest;

    public UnityEngine.UI.Text currentChanceText;

    public int Lives
    {
        get { return lives; }
        set
        {
            if (value < 5)
                lives = value;
            livbar.Refresh();
        }
    }

    private Livesbar livbar;

    [SerializeField]
    private float speed = 3.0f;
    [SerializeField]
    private float jumpforce = 1.0f;

    private bool isGrounded = false;

    private Bullet bullet;

    private ShootableMonster shootMonster;

    private Vector2 movement = new Vector2(1, 1);

    GameObject enemy;

    private CharState State
    {
        get { return (CharState)animator.GetInteger("State"); }
        set { animator.SetInteger("State", (int)value); }
    }

    new private Rigidbody2D rigidbody;
    private Animator animator;
    private SpriteRenderer sprite;

    public Vector3 respawnPoint;

    private void Awake()
    {
        livbar = FindObjectOfType<Livesbar>();
        rigidbody = GetComponent<Rigidbody2D>();
        animator = GetComponent<Animator>();
        sprite = GetComponentInChildren<SpriteRenderer>();

        bullet = Resources.Load<Bullet>("Bullet");
        shootMonster = new ShootableMonster();

        if (SceneManager.GetActiveScene().name == "Level2")
        {
            shootUp = true;
        };
    }

    private void Start()
    {
        audioSource = GetComponent<AudioSource>();
        boss = false;
        isShooting = true;
    }

    private void Update()
    {
        State = CharState.Idle;
        if (Input.GetButtonDown("Fire1") && isShooting == true)
        {
            Shoot();
            audioSource.PlayOneShot(shootSound, 0.3f);
            isShooting = false;
        }
        if (Input.GetButton("Horizontal"))
            Run();
        if (isGrounded && Input.GetButton("Jump"))
            Jump();
        UpdateChances();
        if (FirstBoss.liveCount == 0 && !boss)
        {
            audioSource.PlayOneShot(finalSound, 0.3f);
            boss = true;
        }
    }

    private void FixedUpdate()
    {
        CheckGround();
    }

    void UpdateChances()
    {
        currentChanceText.text = "Попыток: " + chances;
    }

    private void Run()
    {
        Vector3 direction = transform.right * Input.GetAxis("Horizontal");

        transform.position = Vector3.MoveTowards(transform.position, transform.position + direction, speed * Time.deltaTime);

        sprite.flipX = direction.x < 0.0f;

        State = CharState.Run;
    }

    private void Jump()
    {
        State = CharState.Jump;

        rigidbody.AddForce(transform.up * 2.5f, ForceMode2D.Impulse);
    }

    private void CheckGround()
    {
        Vector3 position = transform.position;
        position.x -= 6.06f;
        position.y -= 0.6f;
        Collider2D[] colliders = Physics2D.OverlapCircleAll(position, 0.5f);

        isGrounded = colliders.Length > 1;
    }

    private void Shoot()
    {
        Vector3 position = transform.position;
        position.y += 2.0f;
        position.x += 0.1f;
        Bullet newBullet = Instantiate(bullet, position, bullet.transform.rotation) as Bullet;

        newBullet.Parent = gameObject;
        if (shootUp)
        {
            newBullet.Direction = newBullet.transform.up;
        }
        else
        {
            newBullet.Direction = newBullet.transform.right * (sprite.flipX ? -2.0f : 2.0f);
        }
        StartCoroutine(BlockShoot());
    }

    IEnumerator BlockShoot()
    {
        yield return new WaitForSeconds(1);
        isShooting = true;
    }

    public override void ReceiveDamage()
    {
        audioSource.PlayOneShot(painSound);
        Lives--;

        rigidbody.velocity = Vector3.zero;
        rigidbody.AddForce(transform.up * 6.0f, ForceMode2D.Impulse);
        Debug.Log(lives);

        if (lives < 1)
        {
            transform.position = respawnPoint;
            chances--;
            lives += 5;
            livbar.Refresh();
        }
        if (chances < 1)
        {
            Application.LoadLevel("DeathScene");
        }

    }

    public void OnTriggerEnter2D(Collider2D collider)
    {
        if (collider.tag == "Ground")
        {
            ground = true;
        }

        Bullet bullet = collider.gameObject.GetComponent<Bullet>();
        if (bullet && bullet.Parent != gameObject)
        {

            ReceiveDamage();
        }

        if (collider.tag == "DeathZone")
        {
            transform.position = respawnPoint;
            chances--;
            lives += 5;
            livbar.Refresh();
            if (chances < 1)
            {
                Application.LoadLevel("DeathScene");
            }
        }

        if (collider.tag == "Checkpoint")
        {
            respawnPoint = collider.transform.position;
            Debug.Log("Можешь помирать!");
        }

        if (collider.tag == "Quest")
        {
            quest = true;
            audioSource.PlayOneShot(questVoice);
            Debug.Log(quest);
        }

        if (collider.tag == "TriggerAttack")
        {
            entering = true;
            status = "Самец";
            audioSource.PlayOneShot(enemyVoice);
            //Debug.Log(entering);
        }

        if (collider.tag == "BossTrigger")
        {
            audioSource.PlayOneShot(bossVoice, 0.3f);
        }

        Heart heart = collider.GetComponent<Heart>();
        if (heart)
        {
            audioSource.clip = secondVoice;
            audioSource.Play();
        }

        CheckpointController check = collider.GetComponent<CheckpointController>();
        if (check)
        {
            audioSource.clip = checkpointVoice;
            audioSource.Play();
        }
    }

    public void OnTriggerExit2D(Collider2D collision)
    {
        if (collision.tag == "Ground")
        {
            ground = false;
        }
    }

}

public enum CharState
{
	Idle,
	Run,
	Jump
}
